document.addEventListener("DOMContentLoaded", function () {
  document.querySelectorAll(".cart-item").forEach(function (item) {
    const minusBtn = item.querySelector(".minus-btn");
    const plusBtn = item.querySelector(".plus-btn");
    const qtyInput = item.querySelector(".quantity-input");
    const totalPriceSpan = item.querySelector(".total-price");
    const productId = qtyInput.getAttribute("data-product-id");
    const price = parseInt(item.dataset.price);

    if (!minusBtn || !plusBtn || !qtyInput || !totalPriceSpan || isNaN(price)) {
      console.warn("카트 항목 요소가 누락됨");
      return;
    }

    function updateTotalPrice() {
      const qty = parseInt(qtyInput.value);
      const total = qty * price;
      totalPriceSpan.innerText = total.toLocaleString();
    }

    // 🔄 수동 입력 동기화
    qtyInput.addEventListener("input", function () {
      const newQty = parseInt(qtyInput.value);
      if (!isNaN(newQty)) {
        // ✅ 아래 orderForm input에도 반영
        const orderQtyInput = document.querySelector(`#orderForm input.order-qty-input[data-product-id="${productId}"]`);
        if (orderQtyInput) {
          orderQtyInput.value = newQty;
        }
      }
    });

    // ➖ 버튼
    minusBtn.addEventListener("click", function (e) {
      e.stopPropagation();
      const currentQty = parseInt(qtyInput.value);
      if (currentQty > 1) {
        const newQty = currentQty - 1;
        qtyInput.value = newQty;
        updateTotalPrice();

        const orderQtyInput = document.querySelector(`#orderForm input.order-qty-input[data-product-id="${productId}"]`);
        if (orderQtyInput) {
          orderQtyInput.value = newQty;
        }
      }
    });

    // ➕ 버튼
    plusBtn.addEventListener("click", function (e) {
      e.stopPropagation();
      const currentQty = parseInt(qtyInput.value);

      fetch(`/product/stock?productId=${productId}`)
        .then(response => response.text())
        .then(stock => {
          const available = parseInt(stock);
          if (currentQty + 1 > available) {
            alert(`재고가 부족합니다. 현재 남은 수량: ${available}개`);
          } else {
            const newQty = currentQty + 1;
            qtyInput.value = newQty;
            updateTotalPrice();

            const orderQtyInput = document.querySelector(`#orderForm input.order-qty-input[data-product-id="${productId}"]`);
            if (orderQtyInput) {
              orderQtyInput.value = newQty;
            }
          }
        });
    });
  });

  // ✅ 주문하기 버튼 클릭 시 재고 검사
  const orderForm = document.querySelector("#orderForm");
  if (orderForm) {
	orderForm.addEventListener("submit", async function (e) {
	  e.preventDefault();

	  // ⛳ 카트 내 수량 인풋들 기준
	  const cartItems = document.querySelectorAll(".cart-item");
	  for (const item of cartItems) {
	    const qtyInput = item.querySelector(".quantity-input");
	    const productId = qtyInput.getAttribute("data-product-id");
	    const productName = item.querySelector(".cartp-text-info > div")?.innerText || "상품명 없음";
	    const quantity = parseInt(qtyInput.value);

	    if (isNaN(quantity)) continue;

	    // 🔄 아래 orderForm input도 동기화
	    const orderQtyInput = document.querySelector(`#orderForm input.order-qty-input[data-product-id="${productId}"]`);
	    if (orderQtyInput) orderQtyInput.value = quantity;

	    try {
	      const res = await fetch(`/product/stock?productId=${productId}`);
	      const stock = parseInt(await res.text());

	      if (quantity > stock) {
	        alert(`❗ "${productName}" 재고 초과! 현재 재고: ${stock}개`);
	        return; // ❌ 중단
	      }
	    } catch (err) {
	      alert(`"${productName}" 재고 확인 중 오류`);
	      return;
	    }
	  }

	  // ✅ 다 통과하면 전송
	  orderForm.submit();
	});

  }

  // 수량 변경 & 삭제 submit 버튼 방지
  document.querySelectorAll(".product-update-btn, .product-delete-btn").forEach(function (btn) {
    btn.addEventListener("click", function (e) {
      e.stopPropagation();
    });
  });

  // 수량 변경 시 재고 체크
  document.querySelectorAll("form[action='/cart/updateQuantity']").forEach(function (form) {
    form.addEventListener("submit", async function (e) {
      e.preventDefault();

      const productId = form.querySelector("input[name='productId']").value;
      const quantity = parseInt(form.querySelector("input[name='quantity']").value);

      const response = await fetch(`/product/stock?productId=${productId}`);
      const stock = parseInt(await response.text());

      if (quantity > stock) {
        alert(`재고가 부족합니다. 현재 남은 수량: ${stock}개`);
        return;
      }

      form.submit();
    });
  });
});
