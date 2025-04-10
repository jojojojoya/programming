window.onload = () =>  {
    loadProducts();
    insertProduct(); //여기에 뭘 적으면 문서가 로딩되면서 실행됨
    updateProduct();

    const delBtn =  document.querySelector("#del-btn");

    const pkEl =  document.querySelector('input[name="pk"]');
    delBtn.addEventListener("click",() => {
        deleteProduct(pkEl.value);
        pkEl.value = "";
    })

}

function getProduct(no) {
    //get, ㄴㄴ
    location.href='/products/api/product/' +no;
}



//삭제하는 펑션
function deleteProduct(no) {
    fetch(`/api/product/${no}`, {
        method: "delete",
        headers: {
            'Content-Type': 'application/json'
        }
        // 자바로 만든 객체를 전송. stringify : 문자열 형태로 변경해서 전송
    })   .then(response => response.json())
        .then(result => {
           console.log(result);
            loadProducts();


    })
}

//버튼으로 삭제하는 펑션
function deleteBtnProduct(no) {
    fetch(`/api/product/${no}`, {
        method: "delete",
        headers: {
            'Content-Type': 'application/json'
        }
        // 자바로 만든 객체를 전송. stringify : 문자열 형태로 변경해서 전송
    })   .then(response => response.json())
        .then(result => {
            console.log(result);
            loadProducts();


        })
}



//전체조회 => 값
function loadProducts() {
    fetch('/api/product/all')
        .then(response => response.json())
        .then(products => {
            console.log(products);
            const productList = document.querySelector("#product-list");

            productList.innerHTML = "";
            products.forEach(product => {
                console.log(product);
                const item =
                    document.querySelector(".item.temp").cloneNode(true);
                item.querySelector(".no").innerText = product.p_no;
                item.querySelector(".name").innerText = product.p_name;
                item.querySelector(".price").innerText = product.p_price;


                item.querySelector(".no").dataset.no = product.p_no;
                item.querySelector(".name").dataset.name = product.p_name;
                item.querySelector(".price").dataset.price = product.p_price;


                item.classList.remove("temp");
                productList.appendChild(item);
                item.querySelector(".name").addEventListener("click", () => {
                    getProduct(product.p_no);
                })
                item.lastElementChild.addEventListener("click", () => {
                    console.log(product.p_no);
                    deleteProduct(product.p_no);


                })
            })

        });
}



function insertProduct() {
    const nameEl =
        document.querySelector("#name");
    const priceEl =
        document.querySelector("input[name='p_price']");
    const addBtn = document.querySelector("#add");
    console.log(nameEl);
    console.log(priceEl);
    console.log(addBtn);

    addBtn.addEventListener("click",() => {
        const obj = {
            p_name : nameEl.value,
            p_price : priceEl.value,
            
        };
        console.log(obj);
        console.log(JSON.stringify(obj));
        
        fetch('/products/api/product',{
            // http 요청을 보내는 함수 = fetch
            method : "post",
            headers : {
                'Content-Type' : 'application/json'
            },
            body : JSON.stringify(obj)
            // 자바로 만든 객체를 전송. stringify : 문자열 형태로 변경해서 전송
        }).then(response => {
            console.log(response);

           loadProducts();
            nameEl.value = "";
            priceEl.value = "";
        })

        })}

function updateProduct() {
    const updateBtn = document.querySelector("#update-btn");
    const selectEl = document.querySelector("select[name='p_no']");
    const nameEl = document.querySelector("#up-name");
    const priceEl = document.querySelector("#up-price");

    updateBtn.addEventListener("click", () => {
        const obj = {
            p_no: selectEl.value,
            p_name: nameEl.value,
            p_price: priceEl.value
        };

        fetch('/api/product', {
            method: "put",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(obj)
        }).then(response => {
            loadProducts();
            nameEl.value = "";
            priceEl.value = "";
        });
    });
}
    // 🚨 문제: modal() 함수가 updateProduct() 안에서 정의됨
    function modal() {
        const openModalNo = document.querySelectorAll(".no");
        const openModalButton = document.getElementById('openModal');
        const closeModalButton = document.getElementById('closeModal');
        const myModal = document.getElementById('myModal');

        openModalNo.forEach(noEl => {
            noEl.addEventListener("click", (e) => {
                myModal.showModal();
                document.querySelector(".modal-no").innerText = e.target.dataset.no;
                document.querySelector(".modal-name").innerText = e.target.dataset.name;
                document.querySelector(".modal-price").innerText = e.target.dataset.price;
            });
        });

        openModalButton.addEventListener('click', () => {
            myModal.showModal();
        });

        closeModalButton.addEventListener('click', () => {
            myModal.close();
        });
}
