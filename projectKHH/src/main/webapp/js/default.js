$(document).ready(() => {
    const $map = $('#hokkaido-map');
    const $mapImage = $map.find('img');
    const $shopListContainer = $('#shop-list');
    const $mapPointers = $('.map-pointer');
    let isListVisible = false; // 상태 관리 변수
    let currentRegion = null; // 현재 선택된 지역

    // 초기 포인터 위치 저장 및 설정
    $mapPointers.each(function () {
        const $pointer = $(this);
        const style = $pointer.attr('style');
        const topMatch = style.match(/top:\s*(\d+)%/);
        const leftMatch = style.match(/left:\s*(\d+)%/);

        if (topMatch && leftMatch) {
            $pointer.data('original-top', parseFloat(topMatch[1]));
            $pointer.data('original-left', parseFloat(leftMatch[1]));
        }
    });

    // 지도 크기에 따라 포인터 위치를 동적으로 재조정
    function adjustPointerPositions() {
        const mapWidth = $mapImage.width();
        const mapHeight = $mapImage.height();

        $mapPointers.each(function () {
            const $pointer = $(this);
            const originalLeft = $pointer.data('original-left'); // 초기 left 값 (%)
            const originalTop = $pointer.data('original-top'); // 초기 top 값 (%)

            // 반응형 크기에 따라 포인터 위치 계산
            const left = (mapWidth * originalLeft) / 100;
            const top = (mapHeight * originalTop) / 100;

            $pointer.css({
                left: `${left}px`,
                top: `${top}px`,
            });
        });
    }

    // 창 크기 변경 시 포인터 위치 재조정
    $(window).on('resize', adjustPointerPositions);

    // 초기화 시 포인터 위치 조정
    adjustPointerPositions();

    // 지도 이미지 클릭 이벤트 (포인터 제외)
    $mapImage.on('click', (event) => {
        // 지도 이미지를 클릭하면 초기 상태로 복원
        if (isListVisible) {
            resetToInitialState();
        }
    });

    // 포인터 클릭 이벤트
    $mapPointers.on('click', async function (event) {
        event.stopPropagation(); // 상위 클릭 이벤트로 전파 방지
        const region = $(this).data('region');

        if (!isListVisible) {
            // 최초로 포인터를 클릭하는 경우
            isListVisible = true;
            $map.addClass('reduced');
            $shopListContainer.addClass('visible');
        }

        if (currentRegion !== region) {
            // 다른 포인터를 클릭한 경우에만 리스트 업데이트
            currentRegion = region;
            await updateShopListByRegion(region, $shopListContainer);
        }
    });

    // 가게 리스트를 업데이트하는 함수
    async function updateShopListByRegion(region, $container) {
        try {
            const response = await fetch(`/ShopAddrTypeC?data-region=${region}`, {method: 'GET'});

            if (response.ok) {
                const shopList = await response.json();
                renderShopList(shopList, $container); // 리스트 렌더링
            } else {
                console.error('Failed to fetch shop data:', response.status);
            }
        } catch (error) {
            console.error('Error fetching shop data:', error);
        }
    }

    // 초기 상태로 복원하는 함수
    function resetToInitialState() {
        isListVisible = false;
        currentRegion = null;
        $map.removeClass('reduced');
        $shopListContainer.removeClass('visible').empty(); // 리스트 초기화
    }

    // 리스트를 화면에 렌더링하는 함수
    function renderShopList(shopList, $container) {
        $container.empty(); // 기존 데이터 초기화
        shopList.forEach(shop => {
            const shopElement = $(`
                <div class="simpleList" style="border-top: 1px solid black; border-bottom: 1px solid black; background-color: floralwhite;">
                    <ul style="list-style: none;">
                        <li style="display: none;"><span>${shop.shop_no}</span></li>
                        <li style="list-style: none;">
                            <a class="shopname_a" href="ShopDetailC?no=${shop.shop_no}"><span style="font-size: 20pt; font-weight: bold;">${shop.shop_name}</span></a><br>
                        </li>
                         <li style="list-style: none;"><span><img src="/image/shopImage/${shop.shop_img}" alt="" style="width: 150px; height: 150px"></span></li>
                        <li style="list-style: none;"><span>${shop.shop_content}</span></li>
                        <li style="list-style: none;"><span>${shop.shop_tel}</span></li>
                        <li style="list-style: none;"><span>${shop.shop_opentime}</span></li>
                    </ul>
                </div>
            `);
            $container.append(shopElement);
        });
    }
});

