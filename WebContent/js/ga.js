/* Google Anaylatics e-commerce */

function gaProductDetail(productData) {
    try {
        ga("require", "ec");

        ga("ec:addProduct", {
            "id": productData.id,
            "name": productData.name,
            "category": productData.category,
            "brand": productData.brand,
            "variant": productData.variant
        });

        ga("ec:setAction", "detail");
        ga("send", "pageview");
    } catch(e) {
        return ;
    }
}

function gaAddToCart(aryProductData) {
    try {
        ga("require", "ec");
        ga("set", "currencyCode", "KRW");

        for (var i = 0; i < aryProductData.length; i++) {
            ga("ec:addProduct", {
                "id": aryProductData[i].id,
                "name": aryProductData[i].name,
                "category": aryProductData[i].category,
                "brand": aryProductData[i].brand,
                "variant": aryProductData[i].variant,
                "price": aryProductData[i].price,
                "quantity": aryProductData[i].qty
            });

            ga("ec:setAction", "add");
            ga("send", "event", "UX", "click", "addToCart");
        }
    } catch(e) {
        return ;
    }
}

function gaRemoveFromCart(productData) {
    // 17.10.30 湲곗� �λ컮援щ땲 �곹뭹 ��젣�� 媛쒕퀎�곸쑝濡쒕쭔 媛��ν븯�� �뚮씪誘명꽣瑜� 諛곗뿴濡� 泥섎━�섏� �딆쓬 
    try {
        ga("require", "ec");
        ga("set", "currencyCode", "KRW");

        ga("ec:addProduct", {
            "id": productData.productParentNo,
            "name": productData.productName,
            "category": productData.productCategoryName,
            "brand": productData.productMdName,
            "variant": productData.productOptionName,
            "price": productData.productSalePrice,
            "quantity": productData.productCartCount
        });

        ga("ec:setAction", "remove");
        ga("send", "event", "UX", "click", "removeFromCart");
    } catch(e) {
        return ;
    }
}

function gaCheckout(aryProductData) {
    try {
        ga("require", "ec");
        ga("set", "currencyCode", "KRW");

        for (var i = 0; i < aryProductData.length; i++) {
            ga("ec:addProduct", {
                "id": aryProductData[i].id,
                "name": aryProductData[i].name,
                "category": aryProductData[i].category,
                "brand": aryProductData[i].brand,
                "variant": aryProductData[i].variant,
                "price": aryProductData[i].price,
                "quantity": aryProductData[i].qty
            });

            ga("ec:setAction","checkout", {"step": 1});
            ga("send", "pageview");
        }
    } catch(e) {
        return ;
    }
}

function gaPayment(aryProductData, paymentData) {
    try {
        ga("require", "ec");
        ga("set", "currencyCode", "KRW");

        for (var i = 0; i < aryProductData.length; i++) {
            ga("ec:addProduct", {
                "id": aryProductData[i].id,
                "name": aryProductData[i].name,
                "category": aryProductData[i].category,
                "brand": aryProductData[i].brand,
                "variant": aryProductData[i].variant,
                "price": aryProductData[i].price,
                "quantity": aryProductData[i].qty
            });
        }
        
        ga("ec:setAction", "purchase", {
            "id" : paymentData.id,
            "affiliation" : paymentData.affiliation,
            "revenue" : paymentData.revenue,
            "tax" : paymentData.tax,
            "shipping" : paymentData.shipping,
            "coupon" : paymentData.coupon // �꾩옱�� �뱀뿉 荑좏룿 湲곕뒫�� �놁�留�, �곕━�� �곹뭹 蹂꾨줈 荑좏룿�� 遺숆린 �뚮Ц�� �� 寃쎌슦 �대뼸寃� �댁빞�섎뒗吏� 怨좊��댁빞�� 
        });

        ga("send", "pageview");
    } catch(e) {
        return ;
    }
}

function gaOrderComplete() {
    try {
        ga("require", "ec");
        
        ga("ec:setAction","checkout", {"step": 2});
        
        ga("send", "pageview");
    } catch(e) {
        return ;
    }
}