$(document).ready(
    function slideMenu($) {
        // 泥� �붾㈃ 蹂댁씪 �� �쇱そ, �ㅻⅨ履� �붿궡�� Hide 
        $("a.control_prev").hide();
        $("a.control_next").hide();
        
        /** 
         * categoryList: �꾩껜 移댄뀒怨좊━ 由ъ뒪�� 
         * categoryListCount: 移댄뀒怨좊━ 媛�닔 
         * listLengthSum: 媛� 移댄뀒怨좊━ 由ъ뒪�� 湲몄씠 珥� �� 
         * viewFirstIndex: �붾㈃�� 蹂댁씠�� 泥ル쾲吏� 移댄뀒怨좊━ �몃뜳�� 
         * lastIndex: 泥� �붾㈃�� 蹂댁씠�� 留덉�留� 移댄뀒怨좊━ �몃뜳��(怨좎젙) 
         * viewLastIndex: �붾㈃�� 蹂댁씠�� 留덉�留� 移댄뀒怨좊━ �몃뜳�� 
         * viewLength: 移댄뀒怨좊━ 蹂댁씠�� �곸뿭�� 湲몄씠 
         * rightLength: 留덉�留� 移댄뀒怨좊━ 珥덇낵 �� 留덉�留됱쑝濡� �대룞�� 以� left 媛� 
         **/
        
        var categoryList = new Array();
        var categoryListCount = 0;
        var listLengthSum = 0;
        var viewFirstIndex = 0;
        var lastIndex = 0;
        var viewLastIndex = 0; 
        var viewLength =  0; 
        var rightLength = 0;
        
        // 移댄뀒怨좊━ �щ씪�대뱶 ul�� 湲몄씠 �ㅼ젙 
        setTimeout(function(){
            setSliderLength();
        }, 1000);
        
        // 諛섏쓳�뺤뿉 �곕씪 移댄뀒怨좊━ �щ씪�대뱶 ul�� 湲몄씠 �ㅼ젙 
        $(window).resize(function() {
            setSliderLength();
        });

        function moveLeft() {
            // �щ씪�대뱶 �곸슜 �� �좏슚�� 寃��� 
            if (viewFirstIndex <= 1) {
                $("#sliderMenu ul").animate({
                    left: 0
                }, 200);
                
                // 移댄뀒怨좊━ �몃뜳�� 珥덇린�� 
                viewFirstIndex = 0;
                viewLastIndex = lastIndex;
                
                // �쇱そ �붿궡�� �④� 
                $("a.control_prev").hide();
                return;
            }
            
            // �щ씪�대뱶 �곸슜 
            $("#sliderMenu ul").animate({
                // �� �몃뜳�ㅼ쓽 li width 湲몄씠瑜� �뷀븿 
                left: "+=" + categoryList[viewFirstIndex - 1]
            }, 200, function () {
                viewFirstIndex--;
                viewLastIndex--;
            });
        };

        function moveRight() {
            // �щ씪�대뱶 �곸슜 
            $("#sliderMenu ul").animate({
                left: "-=" + categoryList[viewLastIndex + 1]
            }, 200, function () {
                viewFirstIndex++;
                viewLastIndex++;
                
                if (viewLastIndex === (categoryListCount - 1) || typeof categoryList[viewLastIndex + 1] === "undefined") {
                    // �ㅻⅨ履� �붿궡�� �④� 
                    $("a.control_next").hide();
                    
                    $("#sliderMenu ul").animate({
                        left: "-" + rightLength
                    },200);
                }
                
            });
        };
        
        // �쇱そ �붿궡�� �대┃ 
        $("a.control_prev").click(function () {
            // �щ윭踰� �대┃ �� �몃뜳�� 珥덇린�� 
            if (viewLastIndex >= categoryListCount) {
                viewLastIndex = categoryListCount - 1;
                viewFirstIndex = viewLastIndex - lastIndex;
            }
            
            moveLeft();
            $("a.control_next").show();
        });
        
        // �ㅻⅨ履� �붿궡�� �대┃ 
        $("a.control_next").click(function () {
            // �щ윭踰� �대┃ �� �몃뜳�� 珥덇린�� 
            if (viewFirstIndex < 0 || viewLastIndex < lastIndex) {
                viewFirstIndex = 0;
                viewLastIndex = lastIndex;
            }

            moveRight();
            $("a.control_prev").show();
        });
        
        // #sliderMenu ul �� width媛�, 移댄뀒怨좊━ �몃뜳�� �ㅼ젙 
        function setSliderLength() {
            // ul 湲몄씠 珥덇린�� 
            $("#sliderMenu ul").css("width", 10000);
            // 移댄뀒怨좊━ 蹂댁씠�� �곸뿭 湲몄씠 �ㅼ젙 
            viewLength = $("#sliderMenu.gnbBox").width() - $("#logoArea img").width() - parseInt($(".containerGnb").css("margin-left"));
            lastIndex = 0;
            listLengthSum = 0;
            
            // 由ъ뒪�� 湲몄씠�� 珥� �⑷낵 蹂댁씠�� �곸뿭�� 留덉�留� �몃뜳�� �ㅼ젙 
            $("#sliderMenu ul li").each(function(index) {
                categoryList[index] = $(this).width();
                listLengthSum += categoryList[index];
                
                // 泥� �붾㈃�� 蹂댁씠�� 移댄뀒怨좊━ 留덉�留� �몃뜳�� �ㅼ젙 
                if (listLengthSum < viewLength && index > 0) {
                    lastIndex++;
                }
            });
            
            // 移댄뀒怨좊━ 硫붾돱 諛붿쓽 ul�� 湲몄씠瑜� 由ъ뒪�� �꾩껜 湲몄씠濡� �ㅼ젙 
            $("#sliderMenu ul").css({"width" : listLengthSum, "left" : 0});
            
            // 移댄뀒怨좊━ 留덉�留� �몃뜳�� �섏뼱媛� �� left �ㅼ젙 媛� 
            rightLength = listLengthSum - viewLength;
            
            // 移댄뀒怨좊━ 留덉�留� �몃뜳�� �ㅼ젙 lastIndex 媛믪� 珥덇린�붾� �꾪빐 �④꺼�� 
            viewLastIndex = lastIndex;
            
            // 移댄뀒怨좊━ �꾩껜 由ъ뒪�� 媛�닔 
            categoryListCount = categoryList.length;
            
            // 移댄뀒怨좊━ 由ъ뒪�� 湲몄씠媛� 蹂댁씠�� �곸뿭蹂대떎 湲� 寃쎌슦 �ㅻⅨ履� �붿궡�� Show 
            if (listLengthSum > viewLength) {
                $("a.control_next").show();
            }
        }

        $(".gnbBox a").css("color", "#444");

        // $(".popup-with-zoom-anim").magnificPopup({
        //     type: "inline",
        //     fixedContentPos: false,
        //     fixedBgPos: true,
        //     overflowY: "auto",
        //     closeBtnInside: true,
        //     preloader: false,
        //     midClick: true,
        //     removalDelay: 300,
        //     mainClass: "my-mfp-zoom-in",
        // });

    }
);

function openCate() {
    $("#categoryMobile").animate({
        left: "0",    
    });

    $("#bkscreen").show();
}

function closeCate() {
    $("#categoryMobile").animate({
        left: "-280",
    });

    $("#bkscreen").hide();
}

function closeTopban() {
   $("#topBanner").slideUp();
}

// 怨좉컼�쇳꽣 移댄넚 �꾩씠�� 蹂듭궗 
// var clipboard = new Clipboard(".clipboard");

// clipboard.on("success", function(e) {
//     alert("移댁뭅�ㅽ넚 �뚮윭�� 移쒓뎄 �꾩씠�붽� 蹂듭궗�섏뿀�듬땲��.");
// });

// clipboard.on("error", function(e) {
//     alert("移댁뭅�ㅽ넚 �뚮윭�� 移쒓뎄 �꾩씠�� 蹂듭궗�� �ㅽ뙣�섏��듬땲��." + e.action + "/" + e.trigger + "/" + e.text);

//     $("#kakaoCopyForiOS").click();
// });

