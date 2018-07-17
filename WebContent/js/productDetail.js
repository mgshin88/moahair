// �곹뭹 踰덊샇 
var productNo = $("#productNo").val();
// �꾩닔 �듭뀡 洹몃９ 媛쒖닔 
var essentialOptionCount = 0;

// �듭뀡 媛� �뺤뀛�덈━ 
var optionValueDictionary = {};
// �듭뀡 媛� �꾩닔�щ� �뺤뀛�덈━ 
var optionEssentialDictionary = {};
// �곹뭹 蹂꾨줈 媛뽮퀬 �덈뒗 �듭뀡 媛� �뺤뀛�덈━ 
var productOptionValueDictionary = {};
// �곹뭹 蹂� 異붽�湲덉븸 �뺤뀛�덈━ 
var productAditPriceDictionary = {};

var aryOptionValue = [];
var aryOptionGroup = [];

var objOptionGroup = $(".optionGroupbox");
var objSelectBox = $(".selectOption");

// �곹뭹 �듭뀡 醫낅쪟 
var optionType = $("#productOptionKind").val();

/**
 * 1. optionValueDictionary 
 *      - ex. {2: "Black", 13: "Pink", 14:"Khaki", 16:"Free", ...} 
 * 2. optionValueEssentialDictionary 
 *      - ex. {2: "Y", 13: "N", 14:"Y", 16:"Y", ... } 
 * 3. productOptionValueDictionary 
 *      - ex. [78356: {0: "2", 1: "16"}, 78357: {0: "13", 1: "16"}, ... ] 
 * 4. productAditPriceDictionary 
 *      - ex. [78356: 0, 78357: 3000, ...] 
 */

$(document).ready(function() {

    var productNavigatorTab = $("#productNavigatorTab");
    var productNavigatorLink = $("#productNavigatorTab").find("a");

    // 珥덇린�뷀븷 Tab �쒖꽦��
    var defaultActiveId = $("#itemDetailTab").attr("data-item");
    productNavigatorTab.attr("data-id", defaultActiveId);

    // Tab �대깽�� 諛붿씤��
    productNavigatorLink.on("click", function(e) {
        // �댁쟾�� �쒖꽦�뷀븳 Tab�� View
        var previousActiveId = productNavigatorTab.attr("data-id");
        $("#"+previousActiveId).css("display", "none");

        // �대┃�섏� �딆� Tab 鍮꾪솢�깊솕
        $.each(productNavigatorLink, function(i, val){
            $(val).attr("class", "");
        });
        // �대┃�� Tab �쒖꽦��
        $(e.currentTarget).attr("class", "on");

        var currentActiveId = $(e.currentTarget).attr("data-item");
        $("#"+currentActiveId).css("display", "block");

        productNavigatorTab.attr("data-id", currentActiveId);
    });

    // �섏씠吏��ㅼ씠�� 留곹겕 �앹꽦 
    getPhotoReviewPageLink();
    getInquiryPageLink();
    BrandiUtils.popup("#noticePopup", "/include/custom/images/popup/popup_170920_like.jpg");
    
    // SelectBox trigger 
    $(".selectOption").selectmenu();
    
    // 李몄“�� �듭뀡 洹몃９ �뺤뀛�덈━ �앹꽦 
    $(".optionGroupbox").each(function() {
        var optionGroupValue = $(this).attr("option-group");
        
        aryOptionGroup.push(optionGroupValue);
    });
    
    // 李몄“�� �듭뀡 媛� �뺤뀛�덈━ �앹꽦 
    $(".hdnOptionValueDictionary").map(function() {
        var optionValueKey = $(this).attr("id");
        var optionValue = $(this).val();
        
        optionValueDictionary[optionValueKey] = optionValue;
    });
    
    // 李몄“�� �듭뀡 媛믪쓽 �꾩닔�щ� �뺤뀛�덈━ �앹꽦 
    $(".hdnOptionEssentialDictionary").map(function() {
        var optionValueKey = $(this).attr("id");
        var isEssential = $(this).val();
        
        optionEssentialDictionary[optionValueKey] = isEssential;
    });
    
    // 李몄“�� �곹뭹 蹂� 異붽�湲덉븸 諛곗뿴 �앹꽦 
    $(".hdnProductAditPrice").map(function() {
        var optionValueKey = $(this).attr("id");
        var aditPrice = parseInt($(this).val());
        
        productAditPriceDictionary[optionValueKey] = aditPrice;
    });
    
    // 李몄“�� �곹뭹 蹂� �듭뀡 媛� 諛곗뿴 �앹꽦 
    $(".productAttribute").map(function(i) {
        var productId = $(this).attr("class").split(" ")[1];
        var objOption = $(this).find("li");
        
        var aryOption = objOption.map(function() {
                            return $(this).text(); 
                        }).toArray();
        
        productOptionValueDictionary[productId] = aryOption;
        aryOptionValue[i] = aryOption;
    });
    
    // �꾩닔 �듭뀡洹몃９ 媛쒖닔 
    var count = 0;
    $(".optionGroupArea").find(".optionGroupbox").each(function() {
        if(!$(this).hasClass("isNotEssential")) {
            count++;
        }
    });
    
    essentialOptionCount = count;
    
    // �ㅻ줈媛�湲� 踰꾪듉 �깆쑝濡� ���됲듃諛뺤뒪�� �덉뒪�좊━媛� �⑥븘�덈뒗 寃쎌슦�� ��鍮꾪빐 Refresh 
    refreshOptionGroupArea();
    
    /**
     * �듭뀡洹몃９�먯꽌 �듭뀡�� �좏깮�덉쓣 ��, �ㅼ쓬 �듭뀡洹몃９ �쒖꽦�� 
     * 
     * - showITypeSelectedItem : �낅┰�좏깮�뺥��� �꾩슜 �⑥닔 
     * - showMTypeSelectedItem : 議고빀�� ���� �꾩슜 �⑥닔 
     */
    switch (optionType) {
        case "I":
            $(".selectOption").selectmenu({
                change: function( event, data ) {
                    var groupIndex = parseInt($(this).parents(".optionGroupbox").attr("id"));
                    var selectedOption = $.trim($(this).find("option:selected").text());
                    var productId = data.item.value;
                    var nextIndex = parseInt(groupIndex) + 1;
                    
                    showITypeSelectedItem(groupIndex, productId, selectedOption);
                    
                    // �ㅼ쓬 �꾩닔 �좏깮 洹몃９�� 議댁옱�섎뒗 寃쎌슦 Disabled �댁젣 
                    var objNextOptionGroup = $(".optionGroupbox").eq(nextIndex);
                    
                    if(!objNextOptionGroup.hasClass(".isNotEssential")) {
                        var objNextSelectbox = objNextOptionGroup.find(".selectOption");
                        
                        objNextSelectbox.removeAttr("disabled");
                        objNextSelectbox.selectmenu("refresh", true);
                    }
                }
            });
            
            break;
        default:
            // 泥� 踰덉㎏ �듭뀡洹몃９ �명똿 
            setFirstOptionGroupList();

            $(".selectOption").selectmenu({
                change: function( event, data ) {
                    var selectedValue = data.item.value;
                    var thisIndex = parseInt($(this).parents(".optionGroupbox").attr("id"));
                    var nextIndex = parseInt(thisIndex) + 1;

                    // �ㅼ쓬 �듭뀡 洹몃９ �쒖꽦�� 
                    var objNextOptionGroup = $(".optionGroupbox").eq(nextIndex); 
                    var objNextSelectbox = objNextOptionGroup.find(".selectOption");

                    objNextSelectbox.removeAttr("disabled");
                    objNextSelectbox.selectmenu("refresh", true);

                    // �대떦�섎뒗 �듭뀡 紐⑸줉 議고쉶 
                    var optionList = getOptionValueList(nextIndex, selectedValue);

                    if(optionList.length === 0) {
                        // �섎떒�� �좏깮�� �곹뭹 �몄텧 
                        showMTypeSelectedItem();
                    } else {
                        // �대떦�섎뒗 �듭뀡 紐⑸줉 �명똿 
                        setOptionSelectbox(nextIndex, optionList);
                    }
                }
            });
            
            break;
    }
    
    // �곹뭹 �대�吏� �щ씪�대뱶 
    $("#product-slider").royalSlider({
        autoScaleSlider: false,
        autoScaleSliderWidth: 560,
        autoHeight: false,

        loop: false,
        slidesSpacing: 0,

        imageScaleMode: 'fill',
        imageAlignCenter: true,

        navigateByClick: false,
        numImagesToPreload:2,

        /* Arrow Navigation */
        arrowsNav:true,
        arrowsNavAutoHide: false,
        arrowsNavHideOnTouch: true,
        keyboardNavEnabled: true,
        fadeinLoadedSlide: true,

        /* Thumbnail Navigation */
        controlNavigation: 'thumbnails',
        thumbs: {
            orientation: 'horizontal',
            firstMargin: false,
            appendSpan: true,
            autoCenter: false,
            spacing: 0,
            paddingTop: 0
        }
    });
    
    // �ъ쭊 由щ럭 �섏씠吏��ㅼ씠�� 
    function getPhotoReviewPageLink() {

        // Ajax 
        $.ajax({
            type: "POST",
            url: "/products/getPhotoReviewPageLink",
            dataType: "json",
            data: {
                productNo : productNo
            },
            success: function(data) {
                $("#prPaginationLink").html(data.paginationLink);
                
                $("#prPaginationLink a").each(function() {
                    if($(this).attr("data-ci-pagination-page")) {
                        $(this).addClass("link");
                    }
                });
            }
        });
    }
    
    // Q&A �섏씠吏��ㅼ씠�� 
    function getInquiryPageLink() {

        // Ajax 
        $.ajax({
            type: "POST",
            url: "/products/getInquiryPageLink",
            dataType: "json",
            data: {
                productNo : productNo
            },
            success: function(data) {
                $("#iqPaginationLink").html(data.paginationLink);
                
                $("#iqPaginationLink a").each(function() {
                    if($(this).attr("data-ci-pagination-page")) {
                        $(this).addClass("link");
                    }
                });
            }
        });
    }
    
    /**
     * 泥ル쾲吏� �듭뀡洹몃９�� 援ъ꽦�� �듭뀡紐⑸줉 �명똿 
     * 
     * 泥ル쾲吏� �듭뀡洹몃９�� 援ъ꽦�� �듭뀡紐⑸줉 �명똿 
     * 
     * @author Wookyeong Kim <kimwk@brandi.co.kr> 
     */
    function setFirstOptionGroupList() {
        // n 踰덉㎏ �듭뀡 洹몃９�� �대떦�섎뒗 �듭뀡媛� 紐⑸줉 議고쉶 (�몃뜳�ㅺ컪�쇰줈 議고쉶�쒕떎. ex. 1踰덉㎏ �듭뀡洹몃９ -> 0) 
        var optionList = getOptionValueList(0);

        // n 踰덉㎏ �듭뀡媛� 紐⑸줉�� 援ъ꽦 
        setOptionSelectbox(0, optionList);
    }
    
    /**
     * n踰덉㎏ �듭뀡洹몃９�� 援ъ꽦�� �듭뀡紐⑸줉 議고쉶 
     * 
     * n踰덉㎏ �듭뀡洹몃９�� 援ъ꽦�� �듭뀡紐⑸줉 議고쉶 
     * 
     * @author Wookyeong Kim <kimwk@brandi.co.kr> 
     * @param integer index n踰덉㎏ ���됲듃諛뺤뒪 
     * @param string selectedValue �좏깮�� �듭뀡 
     */
    function getOptionValueList(index, selectedValue) {
        var tempList = [];
        var resultList = [];
        
        // �좏깮�� �듭뀡�� �듭뀡媛� 議고쉶 
        var optionValue = getOptionValue(selectedValue);
        
        for (var i = 0; i < aryOptionValue.length; i++) {
            var pushValue = aryOptionValue[i][index];

            // 以묐났�� �쒓굅�섍퀬 push 
            if ($.inArray(pushValue, tempList) > -1 || (index > 0 && aryOptionValue[i][index-1] !== optionValue)) {
                continue;
            } else {
                tempList.push(pushValue);
            }
        }
        
        for (var i = 0; i < tempList.length; i++) {
            var optionValue = optionValueDictionary[tempList[i]];
            
            if (typeof optionValue != "undefined") {
                resultList.push(optionValue);
            }
        }
        
        return resultList;
    }
    
    /**
     * �듭뀡 ���됲듃諛뺤뒪�� Option 紐⑸줉 �앹꽦 
     * 
     * n踰덉㎏ ���됲듃諛뺤뒪�� <option></option> �쒓렇 �앹꽦 
     * 
     * @author Wookyeogn Kim <kimwk@brandi.co.kr> 
     * @param integer $index n踰덉㎏ ���됲듃諛뺤뒪 
     * @param array $optionList �듭뀡 紐⑸줉 
     */
    function setOptionSelectbox(ntn, optionList) {
        var defaultText = "[" + aryOptionGroup[ntn] + "] ��/瑜� �좏깮�댁＜�몄슂.";
        var customOptions = [];
        var aryProductOptionValue = Object.keys(productOptionValueDictionary);
        
        customOptions.push("<option value='' disabled selected>" + defaultText + "</option>");
        
        for (var index = 0; index < optionList.length; index++) {
            var optionValue = optionList[index];
            
            /**
             * �듭뀡 異붽�湲덉븸 議고쉶 
             */
            var thisAditPrice = "";
            if (essentialOptionCount === ntn+1) {
                var expectProductNo = "";
                var selectedOption =  $(".optionGroupbox").map(function() {
                                          return $(this).find("select").val();
                                      }).toArray();

                var aryResult = [];

                for (var i = 0; i < selectedOption.length; i++) {
                    var selectedOptionValue = getOptionValue(selectedOption[i]);

                    aryResult.push(selectedOptionValue);
                }

                aryResult.push(getOptionValue(optionValue));

                for (var i = 0; i < aryProductOptionValue.length; i++) {
                    var searchKey = aryProductOptionValue[i];

                    var isSame = aryResult.every(function(element, index) {
                                    return element === productOptionValueDictionary[searchKey][index]
                                });

                    if (isSame) {
                        expectProductNo = searchKey;
                    }
                }

                if (expectProductNo.length > 0) {
                    var aditPrice = productAditPriceDictionary[expectProductNo];
                    
                    if (aditPrice !== 0) {
                        thisAditPrice = (aditPrice < 0) ? " (" + $.number(aditPrice) + ")" : " (+" + $.number(aditPrice) +")";
                    }
                };
            }
            
            customOptions.push("<option value='" + optionValue + "'>" + optionValue + thisAditPrice + "</option>");
        }

        // Setting Options 
        var objSelectbox = objSelectBox.eq(ntn);
        
        objSelectbox.html("");
        objSelectbox.append(customOptions.join("")).selectmenu();
        objSelectbox.selectmenu("refresh", true); // Selectbox Refresh 
    }
    
    /**
     * �듭뀡媛믪쑝濡� �듭뀡媛� 踰덊샇 議고쉶 
     * 
     * �듭뀡媛믪쑝濡� �듭뀡媛� 踰덊샇 議고쉶 
     * 
     * @author Wookyeong Kim <kimwk@brandi.co.kr> 
     * @param string selectedValue �듭뀡媛� 
     * @return string optionNo �듭뀡媛� 踰덊샇 
     */
    function getOptionValue(selectedValue) {
        var aryOptionValue = Object.keys(optionValueDictionary);
        
        for (var i = 0; i < aryOptionValue.length; i++) {
            var accessKey = aryOptionValue[i];
            
            if (optionValueDictionary[accessKey] == selectedValue) {
                return accessKey;
            }
        }
    }
    
    /**
     * �낅┰�� - �좏깮�� �곹뭹 移대뱶 �몄텧 
     * 
     * �낅┰�� - �좏깮�� �곹뭹 移대뱶 �몄텧 
     * 
     * @author Wookyeong Kim <kimwk@brandi.co.kr> 
     * @param string groupNo 洹몃９踰덊샇 
     * @param string productId �곹뭹踰덊샇 
     * @param string optionName  
     * @return html 
     */
    function showITypeSelectedItem(groupIndex, productId, optionName) {
        // �곹뭹 移대뱶 �앹꽦 
        var objSelectedItemArea = $(".selectedItemArea");
        var htmlCode = "";
        
        // �듭뀡 洹몃９紐� 議고쉶 
        var optionGroup = aryOptionGroup[groupIndex];
        
        // �대� 異붽��� �듭뀡�� 寃쎌슦�� �섎웾留� + 1 �쒕떎. 
        if (objSelectedItemArea.find("#" + productId).length > 0) {
            var objQty = $("#" + productId).find(".spinnerItem").find("input:text");
            var nowQty = objQty.val();
            
            objQty.val(parseInt(nowQty) + 1);
            
            return false;
        }
        
        htmlCode += '<div class="selectedItem" id="' + productId + '">';
        htmlCode += '   <dl>';
        htmlCode += '       <dt>' + optionGroup + ': </dt>';
        htmlCode += '       <dd>' + optionName + '</dd>';
        htmlCode += '   </dl>';
        htmlCode += '   <li class="spinnerItem">';
        htmlCode += '       <div>';
        // �섎웾 �곸뿭�� display : flex �띿꽦�� �곸슜�섏뼱 �덉뼱, �쒖꽌瑜� �쇰��� 嫄곌씀濡� �대넃�� 
        htmlCode += '           <input type="button" value="x" class="btnDel">';
        htmlCode += '           <input type="button" value="+" class="qtyPlus">';
        htmlCode += '           <input type="text" value="1">';
        htmlCode += '           <input type="button" value="-" class="qtyMinus">';
        htmlCode += '       </div>';
        htmlCode += '   </li>';
        htmlCode += '</div>';

        objSelectedItemArea.append(htmlCode);
    }
    
    /**
     * 議고빀�� - �좏깮�� �곹뭹 移대뱶 �몄텧 
     * 
     * 議고빀�� - 媛� �듭뀡洹몃９�� 紐⑤몢 �먯깋�섏뿬 �좏깮�� �듭뀡媛믪쑝濡� �곹뭹 移대뱶 �몄텧 
     * 
     * @author Wookyeong Kim <kimwk@brandi.co.kr> 
     * @return html 
     */
    function showMTypeSelectedItem() {
        // �좏깮�� �듭뀡�ㅻ줈 �곹뭹�� 議고쉶 
        var selectedOption =  $(".optionGroupbox").map(function() {
                                  return $(this).find("select").val();
                              }).toArray();
                              
        var aryResult = [];
        
        for (var i = 0; i < selectedOption.length; i++) {
            var optionValue = getOptionValue(selectedOption[i]);
            
            aryResult.push(optionValue);
        }
        
        var aryProductOptionValue = Object.keys(productOptionValueDictionary);
        var selectProductNo = "";

        for (var i = 0; i < aryProductOptionValue.length; i++) {
            var searchKey = aryProductOptionValue[i];
            
            var isSame = aryResult.every(function(element, index) {
                            return element === productOptionValueDictionary[searchKey][index]
                        });
            
            if (isSame) {
                selectProductNo = searchKey;
            }
        }

        if (selectProductNo.length == 0) {
            return false;
        }
        
        // �곹뭹 移대뱶 �앹꽦 
        var objSelectedItemArea = $(".selectedItemArea");
        var htmlCode = "";
        
        // �대� 異붽��� �듭뀡�� �덈뒗 寃쎌슦�� �섎웾留� +1 �쒕떎. 
        if (objSelectedItemArea.find("#" + selectProductNo).length > 0) {
            var objQty = $("#" + selectProductNo).find(".spinnerItem").find("input:text");
            var nowQty = objQty.val();
            
            objQty.val(parseInt(nowQty) + 1);
        } else {
            htmlCode += '<div class="selectedItem" id="' + selectProductNo + '">';
            htmlCode += '   <dl>';

            // �낅┰�좏깮�뺤씤 寃쎌슦, �좏깮�듭뀡�� �쒖쇅�� �듭뀡 洹몃９�� 議고쉶�� 
            $(".optionGroupbox").not(".isNotEssential").each(function() {
                var optionGroup = $.trim($(this).attr("option-group"));
                var optionValue = $.trim($(this).find("select").val());

                htmlCode += '<dt>' + optionGroup + ': </dt>';
                htmlCode += '<dd>' + optionValue + '</dd>';
            });

            htmlCode += '   </dl>';
            htmlCode += '   <li class="spinnerItem">';
            htmlCode += '       <div>';
            // �섎웾 �곸뿭�� display : flex �띿꽦�� �곸슜�섏뼱 �덉뼱, �쒖꽌瑜� �쇰��� 嫄곌씀濡� �대넃�� 
            htmlCode += '           <input type="button" value="x" class="btnDel">';
            htmlCode += '           <input type="button" value="+" class="qtyPlus">';
            htmlCode += '           <input type="text" value="1">';
            htmlCode += '           <input type="button" value="-" class="qtyMinus">';
            htmlCode += '       </div>';
            htmlCode += '   </li>';
            htmlCode += '</div>';

            objSelectedItemArea.append(htmlCode);
        }

        // selectedItem �� 異붽��섍퀬 �섎㈃ selectbox 瑜� �ㅼ떆 珥덇린�뷀븳��. 
        refreshOptionGroupArea();
    }
    
    /**
     * �듭뀡 紐⑸줉 珥덇린�� 
     * 
     * �곹뭹 �좏깮�� �꾨즺�섍퀬�섎㈃, �ㅼ떆 泥섏쓬遺��� �듭뀡�� �좏깮�� �� �덈룄濡� Refresh 
     */
    function refreshOptionGroupArea() {
        $(".selectOption").each(function(i) {
            $(this).val($(this).find("option:first").val());
            
            if (i > 0) {
                // �좏깮 �듭뀡�� 寃쎌슦�� �덉쇅泥섎━ 
                if (!$(this).parents(".optionGroupbox").hasClass("isNotEssential")) {
                    $(this).attr("disabled", true);
                }
            }
        });
        
        $(".selectOption").selectmenu("refresh");
    }
    
    /**
     * NEW 5% ���대㉧ 
     * 
     * NEW 5% �곹뭹�� 寃쎌슦 �좎씤源뚯� �⑥� �쒓컙 ���대㉧ �곸슜 
     */
    var objRemainderTime = $("#newRemainderTime");
    
    if (objRemainderTime.length > 0 && $("#remainderTime").length > 0) {
        var endTime = $("#remainderTime").val();
        
        objRemainderTime.countdown(endTime, function(event) {
            $(this).text(
                event.strftime("%H:%M:%S")
            );
    
            // �쒓컙�� 留뚮즺�� 寃쎌슦 �먮옒 媛�寃⑹쑝濡� �먮났 
            if (event.strftime("%H:%M:%S") === "00:00:00") {
                $("#priceArea").remove();
                $("#orginPriceArea").removeClass("display-none");
                
                $("#earningPoint").text($("#orginEarningPoint").val());
            }
        });
    }
    
    $(".popupDownloadCoupon").magnificPopup({
        type: "inline",
        fixedContentPos: false,
        fixedBgPos: true,
        overflowY: "auto",
        showCloseBtn: false, // 而ㅼ뒪���� X 踰꾪듉�� �ъ슜�섍린 �꾪빐 false �곸슜 
        preloader: false,
        midClick: true,
        removalDelay: 300,
        mainClass: "my-mfp-zoom-in"
    });
    
    // 荑좏룿 紐⑤떖李� �リ린 
    $(".closeCouponBtn").click(function() {
        var magnificPopup = $.magnificPopup.instance; 
        magnificPopup.close(); 
    });
});

// �λ컮援щ땲 �닿린 (2017.06.14 源��곌꼍 �묒뾽 �쒖옉) 
function addToCart() {
    var aryCartItem = [];
    
    /**
     * ex. [0]['productId' : 119119, 'qty' : 1] 
     *     [1]['productId' : 119109, 'qty' : 3] 
     */ 
    $(".selectedItemArea").find(".selectedItem").each(function(i) {
        var productId = parseInt($.trim($(this).attr("id")));
        var qty = parseInt($.trim($(this).find(".spinnerItem").find("input:text").val()));

        if (typeof productId !== "number" || typeof qty !== "number") {
            alert("�좏슚�섏� �딆� �묎렐�낅땲��.");
            return false;
        }

        var aryProductAttribute = {};

        aryProductAttribute["productId"] = productId;
        aryProductAttribute["qty"] = qty;

        aryCartItem.push(aryProductAttribute);
    });

    var failedCount = 0;

    if (aryCartItem.length == 0) {
        // �좏깮�섏뼱吏� �곹뭹�� �녿뒗 寃쎌슦, �듭뀡�� �좏깮�대떖�쇰뒗 �쇰읉李� 
        $(".optionGroupArea").find(".optionGroupbox").each(function() {
            var selectedOption = $(this).find("select option:selected").val();

            if (selectedOption.length == 0) {
                // �좏깮�섏� �딆� �듭뀡洹몃９紐� 議고쉶 
                var thisGroup = $(this).attr("option-group");

                alert("[" + thisGroup + "] ��/瑜� �좏깮�댁＜�몄슂.");
                failedCount++;

                return false;
            }
        });

        if (failedCount > 0) {
            return false;
        }
    }

    $.ajax({
        url: "/cart/addToCart",
        type: "POST",
        dataType: "json",
        data: {
            "aryCartItem" : aryCartItem
        },
        success: function(data) {
            if(data.result) {
                // e-commerce ga 異붽� 
                var aryProductData = [];
                var productData = {};

                productData.id = $.trim($("#gaProductNo").val());
                productData.name = $.trim($("#gaProductName").val());
                productData.category = $.trim($("#gaProductCategory").val());
                productData.price = $.trim($("#gaProductPrice").val());
                productData.brand = $.trim($("#gaMarketName").val());

                for (var i = 0; i < aryCartItem.length; i++) {
                    productData.quantity = aryCartItem[i].qty;
                }

                // 異붽��� �듭뀡 N 媛� 留뚰겮 push 
                $(".selectedItemArea").find(".selectedItem").each(function(i) {
                    var variable = "";

                    $(this).find("dd").each(function(j) {
                        var tempVariable = $.trim($(this).text());

                        if (j > 0) {
                            variable = variable + "/" + tempVariable;
                        } else {
                            variable = tempVariable;
                        }
                    });

                    // ex. Black/Free 
                    productData.variant = variable;
                    aryProductData.push(productData);
                });

                gaAddToCart(aryProductData);

                if(!confirm("�좏깮�섏떊 �곹뭹�ㅼ씠 �뺤긽�곸쑝濡� �λ컮援щ땲�� �닿꼈�듬땲��. \n吏�湲� �λ컮援щ땲�⑥쑝濡� �대룞�섏떆寃좎뒿�덇퉴?")) {
                    return false;
                }
                
                location.href = "/cart";
                return false;
            } else {
                alert(data.message);
                return false;
            }
        },
        error:function(request,status,error) {
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });
}

// 諛붾줈援щℓ (2017.06.16 源��곌꼍 �묒뾽 �쒖옉) 
function makeOrderPage() {
    var aryOrderItem = [];

    /**
     * ex. [0]['productId' : 119119, 'qty' : 1] 
     *     [1]['productId' : 119109, 'qty' : 3] 
     */ 
    $(".selectedItemArea").find(".selectedItem").each(function(i) {
        var productId = parseInt($.trim($(this).attr("id")));
        var qty = parseInt($.trim($(this).find(".spinnerItem").find("input:text").val()));

        if (typeof productId !== "number" || typeof qty !== "number") {
            alert("�좏슚�섏� �딆� �묎렐�낅땲��.");
            return false;
        }

        var aryProductAttribute = {};

        aryProductAttribute["productId"] = productId;
        aryProductAttribute["qty"] = qty;

        aryOrderItem.push(aryProductAttribute);
    });

    var failedCount = 0;

    if (aryOrderItem.length === 0) {
        // �좏깮�섏뼱吏� �곹뭹�� �녿뒗 寃쎌슦, �듭뀡�� �좏깮�대떖�쇰뒗 �쇰읉李� 
        $(".optionGroupArea").find(".optionGroupbox").each(function() {
            var selectedOption = $(this).find("select option:selected").val();

            if (selectedOption.length == 0) {
                // �좏깮�섏� �딆� �듭뀡洹몃９紐� 議고쉶 
                var thisGroup = $(this).attr("option-group");

                alert("[" + thisGroup + "] ��/瑜� �좏깮�댁＜�몄슂.");
                failedCount++;

                return false;
            }
        });

        if (failedCount > 0) {
            return false;
        }
    }
    
    /**
     * ajax 濡� �좏슚�� 泥댄겕 �� �� �꾩넚 
     * 
     * �쇱쓣 �섍린硫댁꽌 �좏슚�깆쓣 泥댄겕�섎㈃, fail �� �ㅼ떆 �붾㈃�� history.back �댁빞 �섎뒗�� 
     * 洹몃윭硫� �좏깮�� �듭뀡�ㅼ씠 �� �좎븘媛��섍� �섎뒗 �댁뒋媛� �덉쓣 寃� 媛숈쓬 
     */
    $.ajax({
        url: "/checkout/checkForCheckout",
        dataType: "json",
        type: "POST",
        data: {
            aryOrderItem : aryOrderItem
        },
        success: function(data) {
            if(data.result) {
                goCheckout(aryOrderItem);
            } else {
                alert(data.message);
                return false;
            }
        }
    });
}

// 二쇰Ц�� �묒꽦 �섏씠吏�濡� �대룞 
function goCheckout(aryOrderItem) {
    var objCheckoutForm = $("#checkout");
    
    objCheckoutForm.attr("action", "/checkout/checkout");
    objCheckoutForm.attr("enctype", "application/json");
    
    for (var i = 0; i < aryOrderItem.length; i++) {
        var object = aryOrderItem[i];
        var productId = object["productId"];
        var qty = object["qty"];
        
        objCheckoutForm.append("<input name='aryOrderItem[" + i +"][productId]' value='" + productId + "'>");
        objCheckoutForm.append("<input name='aryOrderItem[" + i +"][qty]' value='" + qty + "'>");
    }
    
    objCheckoutForm.submit();
}

// 鍮꾨줈洹몄씤 �곹깭�� 寃쎌슦 濡쒓렇�� �섏씠吏�濡� �대룞 
function goToLogin() {
    if (!confirm("濡쒓렇�몄씠 �꾩슂�� �쒕퉬�ㅼ엯�덈떎. \n濡쒓렇�� �섏씠吏�濡� �대룞�섏떆寃좎뒿�덇퉴?")) {
        return false;
    }
    
    location.href = "/account/login";
    return false;
}

// �� �꾩튂濡� �붾㈃ �대룞 
function moveToElement(elementName, isClass) {
    var selector = isClass ? "." + elementName : "#" + elementName;

    $("html, body").animate({
        scrollTop: $(selector).offset().top
    }, 500);
}

// �좏깮�� �듭뀡 ��젣 
$(document).delegate(".btnDel", "click", function() {
    if (!confirm("�좏깮�섏떊 �곹뭹�� ��젣�섏떆寃좎뒿�덇퉴?")) {
        return false;
    }
    
    $(this).parents(".selectedItem").remove();
});


/**
 * �섎웾 而⑦듃濡� 湲곕뒫 �묒뾽 �쒖옉 
 */

// �섎웾�먮뒗 臾댁“嫄� 1 �댁긽�� �レ옄留� 諛쏆븘�ㅼ엫 
$(document).delegate(".spinnerItem input:text", "keyup", function() {
    var value = $.trim($(this).val());
    
    if (value.length == 0) {
        $(this).val(1);
        return false;
    }
    
    var replaceValue = value.replace(/\D/g, "");
    var result = replaceValue.replace(/^0/, 1);
    
    $(this).val(result);
    return false;
});

$(document).delegate(".spinnerItem input:text", "focusout", function() {
    var value = $.trim($(this).val());
    
    if (value.length == 0) {
        $(this).val(1);
        return false;
    }
    
    var replaceValue = value.replace(/\D/g, "");
    var result = replaceValue.replace(/^0/, 1);
    
    $(this).val(result);
    return false;
});

// �섎웾 留덉씠�덉뒪 �섍린 
$(document).delegate(".qtyMinus", "click", function() {
    var objQty = $(this).parents(".spinnerItem").find("input:text");
    var qty = $.trim(objQty.val());
    
    if (qty.length == 0 || isNaN(parseInt(qty)) || parseInt(qty) - 1 <= 0) {
        objQty.val(1);
        return false;
    }
    
    objQty.val(parseInt(qty) - 1);
    return false;
});

// �섎웾 �뚮윭�� �섍린 
$(document).delegate(".qtyPlus", "click", function() {
    var objQty = $(this).parents(".spinnerItem").find("input:text");
    var qty = $.trim(objQty.val());
    
    if (qty.length == 0 || isNaN(parseInt(qty))) {
        objQty.val(1);
        return false;
    }
    
    objQty.val(parseInt(qty) + 1);
    return false;
});

/**
 * �섎웾 而⑦듃濡� 湲곕뒫 �묒뾽 醫낅즺 
 */

// �ъ쭊 由щ럭 �섏씠吏��ㅼ씠�� 留곹겕 �대┃ �대깽�� 
$(document).delegate("#prPaginationLink div", "click", function(e) {

    var page = $(this).find("a").attr("data-ci-pagination-page");
    var type = $(this).find("a").attr("type");
    var first = $(this).find("a").attr("first");
    var last = $(this).find("a").attr("last");
    
    $.ajax({
        type: "POST",
        url: "/products/getPhotoReviewPageView",
        dataType: "json",
        data: {
            productNo : productNo,
            page : page,
            type : type,
            first : first,
            last : last
        },
        success: function(data) {
            if (data.result) {
                // 泥ル쾲吏� �뚮씪誘명꽣 '�붿냼紐�', �먮쾲吏� �뚮씪誘명꽣 '�대옒�� �щ�'  
                moveToElement("itemReviewInfo", false);
                
                $("#ajaxReviewArea").html("");
                $("#ajaxReviewArea").html(data.htmlCode);
                $("#prPaginationLink").html(data.paginationLink);
                $("#prPaginationLink a").each(function() {
                    if ($(this).attr("data-ci-pagination-page")) {
                        $(this).addClass("link");
                    }
                });
            } else {
                return false;
            }
        }
    });
});

// Q&A 由щ럭 �섏씠吏��ㅼ씠�� 留곹겕 �대┃ �대깽�� 
$(document).delegate("#iqPaginationLink div", "click", function(e) {

    var page = $(this).find("a").attr("data-ci-pagination-page");
    var type = $(this).find("a").attr("type");
    var first = $(this).find("a").attr("first");
    var last = $(this).find("a").attr("last");
    
    $.ajax({
        type: "POST",
        url: "/products/getInquiryPageView",
        dataType: "json",
        data: {
            productNo : productNo,
            page : page,
            type : type,
            first : first,
            last : last
        },
        success: function(data) {
            if(data.result) {
                // 泥ル쾲吏� �뚮씪誘명꽣 '�붿냼紐�', �먮쾲吏� �뚮씪誘명꽣 '�대옒�� �щ�' 
                moveToElement("itemQna", false);
                
                $("#inquiryArea").html("");
                $("#inquiryArea").html(data.htmlCode);

                $("#iqPaginationLink").html(data.paginationLink);
                $("#iqPaginationLink a").each(function() {
                    if($(this).attr("data-ci-pagination-page")) {
                        $(this).addClass("link");
                    }
                });
            } else {
                return false;
            }
        }
    });
});

/**
 * �좎씤 荑좏룿 諛쏄린 �앹뾽李� �몄텧 
 */
function popupDownloadCoupon() {
    $(".popupDownloadCoupon").click();
}

/**
 * �좎씤 荑좏룿 諛쏄린 
 */
$(".downloadCoupon").click(function(e) {
    var couponId = $(this).attr("data-coupon-id");
    var objImg = $(this).find("img");
    var objCouponTxt = $(this).find(".downloadCouponTxt");
    var objBtn = $(this);
    
    // �대� 諛쒓툒諛쏆� 荑좏룿�� 寃쎌슦 
    if (objBtn.hasClass("cursorNotAllowed")) {
        return ;
    }
    
    if (couponId.length === 0) {
        alert("�댁슜�� 遺덊렪�� �쒕젮 二꾩넚�⑸땲��. \n荑좏룿 �ㅼ슫濡쒕뱶瑜� �� �� �놁뒿�덈떎.");
        return ;
    }
    
    // 以묐났 �붿껌�� 留됯린 �꾪빐 踰꾪듉 Disable 
    objBtn.addClass("cursorNotAllowed");
    
    $.ajax({
        type: "POST",
        url: "/coupon/couponDownload",
        dataType: "json",
        data: {
            couponId : couponId
        },
        success: function(data) {
            if (data.result) {
                alert("荑좏룿 諛쒓툒�� �꾨즺�섏뿀�듬땲��.");
                
                // �대� 諛쒓툒諛쏆� 荑좏룿 泥섎━ 
                objCouponTxt.text("諛쒓툒諛쏆� 荑좏룿");
                objCouponTxt.addClass("downloadedCoupon");
                objCouponTxt.addClass("cursorNotAllowed");
                objBtn.addClass("cursorNotAllowed");
                objImg.attr("src", "/include/custom/images/a_icon_coupon_product_arrow_s.png");
            } else {
                alert(data.message);
                objBtn.removeClass("cursorNotAllowed");
                return ;
            }
        },
        error:function(request,status,error) {
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            objBtn.removeClass("cursorNotAllowed");
        }
    });
});