var BrandiUtils = (function() {

    // jquery plugin �ㅽ��쇰룄 異붽��� 2017-08-24 chunbs@brandi.co.kr
    $.fn.onlyNumber = function(opt){
        this.on('keydown keyup change', function(){
            var value = $.trim(this.value);

            if ((this.validity) && (!this.validity.valid))
            {
               //if html5 validation says it's bad: it's bad
               return false;
            }

            if (this.length == 0) {
                this.val = "";
                return false;
            }

            var replaceValue = value.replace(/\D/g, "");
            var result = replaceValue.replace(/^0/, 0);

            this.value = result;
        });

        return this;
    }


    function getUrlParameter(sParam) {
        var sPageURL = decodeURIComponent(window.location.search.substring(1)),
            sURLVariables = sPageURL.split("&"),
            sParameterName,
            i;

        for (i = 0; i < sURLVariables.length; i++) {
            sParameterName = sURLVariables[i].split("=");

            if (sParameterName[0] === sParam) {
                return sParameterName[1] === undefined ? true : sParameterName[1];
            }
        }
    }

    // 紐⑤컮��, PC 援щ텇
    function getPlatform()
    {
        var isMobile = /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent) ? true : false;

        if(isMobile) {
            return "mobile";
        } else {
            return "pc";
        }
    }

    // �대찓�� 洹쒖튃 �뺤씤
    function isValidEmailAddress(emailAddress) {
        var pattern = /^([a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+(\.[a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+)*|"((([ \t]*\r\n)?[ \t]+)?([\x01-\x08\x0b\x0c\x0e-\x1f\x7f\x21\x23-\x5b\x5d-\x7e\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|\\[\x01-\x09\x0b\x0c\x0d-\x7f\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))*(([ \t]*\r\n)?[ \t]+)?")@(([a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.)+([a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.?$/i;
        return pattern.test(emailAddress);
    }

	// �됰꽕�� 洹쒖튃 �뺤씤
    function isValidNickname(nickname) {
        if (nickname.length < 4 || nickname.length > 15) {
            return false;
        }

        var pattern = /^[a-z0-9._]*$/;
        var aa = pattern.test(nickname);

        return pattern.test(nickname);
    }

    // 鍮꾨�踰덊샇 洹쒖튃 泥댄겕 (8�먯씠�� 3媛�吏� 議고빀 �뚮Ц��,��臾몄옄, �レ옄, �뱀닔湲고샇)
    // return true or errerMessage
    function checkPasswordRule(password) {
        var upperAlphabet = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
        var lowerAlphabet = 'abcdefghijklmnopqrstuvwxyz';
        var numbers = '0123456789';
        var specialWords = ',.=~!@#$%^&*()_+`{}|<>?;:\'"';

        if( password === undefined ) password = '';
        if( password.length < 8 ) return '鍮꾨�踰덊샇�� 理쒖냼 8�� �댁긽 �낅젰�� 二쇱꽭��.';
        if( password.length > 20 ) return '鍮꾨�踰덊샇�� 理쒕� 20�먭퉴吏� �낅젰 媛��ν빀�덈떎.';

        var cntUpper = 0;
        var cntLower = 0;
        var cntNumber = 0;
        var cntSpecial = 0;
        var caseLen = 0;

        for( var i=0,len=password.length;i<len;i++ ) {
            if( upperAlphabet.indexOf(password.charAt(i)) >= 0 ) cntUpper ++;
            if( lowerAlphabet.indexOf(password.charAt(i)) >= 0 ) cntLower ++;
            if( numbers.indexOf(password.charAt(i)) >= 0 ) cntNumber ++;
            if( specialWords.indexOf(password.charAt(i)) >= 0 ) cntSpecial ++;
        }
        if( cntUpper > 0 ) caseLen++;
        if( cntLower > 0 ) caseLen++;
        if( cntNumber > 0 ) caseLen++;
        if( cntSpecial > 0 ) caseLen++;

        if( caseLen < 3 ) return '鍮꾨�踰덊샇�� �곷Ц ���뚮Ц��, �レ옄, �뱀닔臾몄옄 以� 3媛�吏� �댁긽�� �ъ슜�� 二쇱꽭��.';
        return true;
    }

    // �� �� �먮━ 肄ㅻ쭏 泥섎━
    function setNumberFormat(price) {
        var result = String(price).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        return result;
    }

    // �대떦 ���됲꽣 �꾩튂濡� �붾㈃ �대룞
    function moveToElement(elementName, isClass) {
        var selector = isClass ? "." + elementName : "#" + elementName;

        $("html, body").animate({
            scrollTop: $(selector).offset().top
        }, 500);
    }

    // �� �� �먮━ 肄ㅻ쭏 �쒓굅
    function removeNumberFormat(price) {
        var result = parseInt(price.replace(/,/g, ""));
        return result;
    }

        /**
     *
     * �앹뾽李� �대�吏�寃쎈줈 媛덉븘 �쇱슦湲�
     *
     * @author Wookyun park <parkwk@brandi.co.kr>
     * @imageSrc {string} �대�吏� 媛덉븘�� 寃쎈줈
     * @elementId {string} popup element�� �꾩씠��
     * @example brandi.popup("#popupNotice" , /include/image/example.jpg");
     */
    function popup(elementId, imageSrc) {
        var defaultImageSrc = "/include/custom/images/popup/popup_170706.png";

        $(".popup-with-zoom-anim").magnificPopup({
            type: "inline",
            fixedContentPos: false,
            fixedBgPos: true,
            overflowY: "auto",
            closeBtnInside: true,
            preloader: true,
            midClick: true,
            removalDelay: 300,
            mainClass: "my-mfp-zoom-in",
            callbacks: {
                beforeOpen: function() {
                    $(elementId).find("img").attr("src", imageSrc);
                },
                elementParse: function(item) {
                    // Function will fire for each target element
                    // "item.el" is a target DOM element (if present)
                    // "item.src" is a source that you may modify

                },
                open: function() {
                    // Will fire when this exact popup is opened
                    // this - is Magnific Popup object
                },
                close: function() {
                    // Will fire when popup is closed
                    // $(elementId).find("img").attr("src", defaultImageSrc);
                }
            }
        });
    };

    /**
     * magnificPopup�먯꽌 �대�吏��앹뾽�� 吏��먰븯吏�留� css �섏젙�� ��湲�
     *
     * @author Wookyun park <parkwk@brandi.co.kr>
     * @imageSrc {string} �대�吏� 媛덉븘�� 寃쎈줈
     * @elementId {string} imagePopup element�� �꾩씠��
     * @example brandi.imagePopup("#popupNotice" , /include/image/example.jpg");
     */
    var imagePopup = function(elementId, imageSrc) {
        $(elementId).magnificPopup({
            mainClass: "popup-with-zoom-anim",
            closeBtnInside: true,
            items: [
                {
                    src: imageSrc
                }
            ],
            type: "image",
            callbacks: {
                elementParse: function(item) {
                    // console.log(item);
                }
            }
        });
    };

    /**
     * form�� 媛믩뱾�� 異붿텧�� object濡� 諛섑솚
     *
     * @author Chun bosung <chunbs@brandi.co.kr>
     * @element {mixed} 媛믪쓣 異붿텧�� form �섎━癒쇳듃 or JQuery 媛앹껜
     * @example BrandiUtils.getFormData( $('form') );
     */
    var getFormData = function( element ) {
        var paramObj = {};
        $.each($(element).serializeArray(), function(_, kv) {
          if (paramObj.hasOwnProperty(kv.name)) {
            paramObj[kv.name] = $.makeArray(paramObj[kv.name]);
            paramObj[kv.name].push(kv.value);
          }
          else {
            paramObj[kv.name] = kv.value;
          }
        });
        return paramObj;
    }

    /**
     * �몃뵒耳��댄꽣瑜� 蹂댁뿬以�
     *
     * @author Chun bosung <chunbs@brandi.co.kr>
     * @element {mixed} 媛믪쓣 異붿텧�� form �섎━癒쇳듃 or JQuery 媛앹껜
     * @example BrandiUtils.getFormData( $('form') );
     */

    var showIndicator = function( msg ) {
        $('body').loadingIndicator({msg:msg});
    }

    /**
     * �몃뵒耳��댄꽣瑜� 蹂댁뿬以�
     *
     * @author Chun bosung <chunbs@brandi.co.kr>
     * @element {mixed} 媛믪쓣 異붿텧�� form �섎━癒쇳듃 or JQuery 媛앹껜
     * @example BrandiUtils.getFormData( $('form') );
     */

    var indicator = null;
    var showIndicator = function( msg ) {
        if( !$('body').data('loadingIndicator') ) {
            $('body').loadingIndicator({msg:msg});
        } else {
            $('.loading-indicator-msg').html(msg);
            $('body').data('loadingIndicator').show();
        }
    }

    var hideIndicator = function() {
        $('body').data('loadingIndicator').hide();
    }


    return {
        getUrlParameter : getUrlParameter
        , getPlatform : getPlatform
        , isValidEmailAddress : isValidEmailAddress
        , isValidNickname : isValidNickname
        , setNumberFormat : setNumberFormat
        , moveToElement : moveToElement
        , removeNumberFormat : removeNumberFormat
        , popup : popup
        , checkPasswordRule : checkPasswordRule
        , getFormData : getFormData
        , showIndicator : showIndicator
        , hideIndicator : hideIndicator
    };
})();


BrandiUtils.popup("#popupApp", "/include/custom/images/popup/popup_170706.png");