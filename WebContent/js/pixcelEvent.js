/* Facebook Fixcel */
$(document).ready(function() {
    // PC 移댄뀒怨좊━ 紐⑸줉 
    $("#sliderMenu > ul > li > a").click(function() {
        fbq("track", "ViewContent", {"clickPosition":"categoryMenuPc"});
//        console.log("categoryMenuPc");
    });
    // MOBILE 移댄뀒怨좊━ 紐⑸줉 
    $("#categoryMobile > a").click(function() {
        fbq("track", "ViewContent", {"clickPosition":"categoryMenuMobile"});
//        console.log("categoryMenuMobile");
    });
    // �곹뭹 紐⑸줉 �대┃ 
    $(".boxImgItem > a, .txtOverflowHidden > a").click(function() {
        fbq("track", "ViewContent", {"clickPosition":"productList"});
//        console.log("productList");
    });
    // �곹뭹 紐⑸줉 ���� �꾩씠肄� �대┃ 
    $(".boxMarketItem > a").click(function() {
        fbq("track", "ViewContent", {"clickPosition":"productListSeller"});
//        console.log("productListSeller");
    });
    // �곹뭹�곸꽭 ���� �섏씠吏� 媛�湲� 
    $(".marketInfo a").click(function() {
        fbq("track", "ViewContent", {"clickPosition":"productDetailSeller"});
//        console.log("productDetailSeller");
    });
    // �붾낫湲� 踰꾪듉 
    $("a.btnMoreHome").click(function() {
        fbq("track", "ViewContent", {"clickPosition":"moreButton"});
//        console.log("moreButton");
    });
    // �곷떒 �ㅼ튂 諛곕꼫 
    $("#containerTopban > a").click(function() {
        fbq("track", "ViewContent", {"clickPosition":"topInstallBanner"});
//        console.log("topInstallBanner");
    });
    // �덉씠�� �ㅼ튂 諛곕꼫 
    $("#popupApp > a").click(function() {
        fbq("track", "ViewContent", {"clickPosition":"layerInstallBanner"});
//        console.log("layerInstallBanner");
    });
    // ���� > 李쒗븯湲� 
    $(".btnMarketProfile > a").click(function() {
        fbq("track", "ViewContent", {"clickPosition":"sellerLikeButton"});
//        console.log("sellerLikeButton");
    });
    
    // �곹뭹�곸꽭 > 李쒗븯湲� 
    $("a.btnFavorItem").click(function() {
        fbq("track", "AddToCart", {"clickPosition":"likeButton"});
//        console.log("likeButton");
    });
    // �곹뭹�곸꽭 > �λ컮援щ땲 
    $("a.btnCartItem").click(function() {
        fbq("track", "AddToCart", {"clickPosition":"cartButton"});
//        console.log("cartButton");
    });
    
    // �곹뭹�곸꽭 > 諛붾줈援щℓ 
    $("a.btnBuyItem").click(function() {
        fbq("track", "InitiateCheckout", {"clickPosition":"buyButton"});
//        console.log("buyButton");
    });
    
    // MOBILE 硫붿씤 > �곷떒諛곕꼫 
    $("#jssor_1 a").click(function() {
        fbq("trackCustom", "MainBanner", {"clickPosition":"mainBannerMobile"});
//        console.log("mainBannerMobile");
    });
    // PC 硫붿씤 > �곷떒諛곕꼫 
    $("#jssor_2 a").click(function() {
        fbq("trackCustom", "MainBanner", {"clickPosition":"mainBannerPc"});
//        console.log("mainBannerPc");
    });
});