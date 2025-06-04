document.addEventListener("DOMContentLoaded", function () {
  // =============================
  // 1. 상품 수량 조절 버튼
  // =============================
  const minusBtn = document.querySelector(".minus-amount-btn");
  const plusBtn = document.querySelector(".plus-amount-btn");
  const visibleInput = document.querySelector(".amount-input");
  const hiddenInput = document.querySelector("#cart-quantity-input");
  const cartAddInput = document.querySelector("#cart-add-quantity-input"); 

  if (minusBtn && plusBtn && visibleInput && hiddenInput) {
    minusBtn.addEventListener("click", function () {
      let val = parseInt(visibleInput.value);
      if (val > 1) val--;
      visibleInput.value = val;
      hiddenInput.value = val;
	  if(cartAddInput) cartAddInput.value = val;
    });

    plusBtn.addEventListener("click", function () {
      let val = parseInt(visibleInput.value);
      val++;
      visibleInput.value = val;
      hiddenInput.value = val;
	  if(cartAddInput) cartAddInput.value = val;
    });

    visibleInput.addEventListener("input", function () {
		const val = parseInt(visibleInput.value);
		if(!isNaN(val) && val >0) {
			
      hiddenInput.value = visibleInput.value;
	  if(cartAddInput) cartAddInput.value = val;
		}
    });
  }
    const buyForm = document.querySelector("form[action='/buydirect']");
    if (buyForm) {
      buyForm.addEventListener("submit", async function (e) {
        e.preventDefault();

        const productId = buyForm.querySelector("input[name='productId']").value;
        const quantity = parseInt(buyForm.querySelector("input[name='quantity']").value);

        try {
          const response = await fetch(`/product/stock?productId=${productId}`);
          const stock = parseInt(await response.text());

          if (quantity > stock) {
            alert(`재고가 부족합니다. 현재 재고: ${stock}개`);
            return;
          }

          buyForm.submit();
        } catch (error) {
          console.error("buyForm 재고 확인 실패:", error);
          alert("서버 오류로 재고를 확인할 수 없습니다.");
        }
      });
    }

    // =============================
    // 장바구니 재고 체크
    // =============================
    const cartForm = document.querySelector("form[action='/cart/add']");
    if (cartForm) {
      cartForm.addEventListener("submit", async function (e) {
        e.preventDefault();


		    const productId = cartForm.querySelector("input[name='productId']").value;
		    
		    // 🔥 visible input에서 직접 최신값 가져와서 hidden에 반영
			const visibleInput = document.querySelector(".amount-input");
			const quantity = parseInt(visibleInput.value);

			const cartAddInput = document.querySelector("#cart-add-quantity-input");
			if (cartAddInput) {
			  cartAddInput.value = quantity;  // 반드시 여기서 수동으로 동기화해줘야 돼
			}

			  try {
		      const response = await fetch(`/product/stock?productId=${productId}`);
		      const stock = parseInt(await response.text());

		      if (quantity > stock) {
		        alert(`재고가 부족합니다. 현재 재고: ${stock}개`);
		        return;
		      }

		      cartForm.submit();
		    } catch (error) {
		      console.error("cartForm 재고 확인 실패:", error);
		      alert("서버 오류로 재고를 확인할 수 없습니다.");
		    }
		  });
		}
  });