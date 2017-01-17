/*======================================= 全局通用的util,这里没有做成模块化 */
function isType(obj, type) {
    var clas = Object.prototype.toString.call(obj).slice(8, -1);
    return obj !== undefined && obj !== null && clas === type;
}
isType.Array = "Array";
isType.Object = "Object";
isType.Number = "Number";
isType.String = "String";
isType.Boolean = "Boolean";

Date.Parse = function(str, format){

};
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,                 //月份
        "d+": this.getDate(),                    //日
        "h+": this.getHours(),                   //小时
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};
Date.prototype.addDay = function (num) {
    return new Date(this.getFullYear(), this.getMonth(), this.getDate() + num, this.getUTCHours(), this.getUTCMinutes(), this.getUTCSeconds());
};
Date.prototype.addMonth = function (num) {
    return new Date(Date.UTC(this.getFullYear(), this.getMonth() + num, this.getDate(), this.getUTCHours(), this.getUTCMinutes(), this.getUTCSeconds()));
};
Date.prototype.addYear = function(num){
    return new Date(Date.UTC(this.getFullYear()+num, this.getMonth(), this.getDate(), this.getUTCHours(), this.getUTCMinutes(), this.getUTCSeconds()));
};
function maskCardId(cardId){
    return cardId.substr(6, 4);
}
function maskMobileNo(mobileNo){
    if(mobileNo==""||mobileNo==null){
        return "";
    }
    return mobileNo.substr(0, 3)+"****"+mobileNo.substr(7, 11)
}

function dateParse(dateStr) {
  if(dateStr){
    if(dateStr.length === 8){
      return new Date(dateStr.substr(0, 4), dateStr.substr(4, 2)-1, dateStr.substr(6, 2));
    }else if(dateStr.length === 14){
      return new Date(dateStr.substr(0, 4), dateStr.substr(4, 2)-1, dateStr.substr(6, 2),
        dateStr.substr(8, 2), dateStr.substr(10, 2), dateStr.substr(12, 2));
    }else{
      return null;
    }
  }
    return new Date(dataStr.substr(0, 4), dataStr.substr(4, 2)-1, dataStr.substr(6, 2),
        dataStr.substr(8, 2), dataStr.substr(10, 2), dataStr.substr(12, 2));
}
function convertCurrency(currencyDigits) {

    var MAXIMUM_NUMBER = 99999999999.99;  //最大值
    // 定义转移字符
    var CN_ZERO = "零";
    var CN_ONE = "壹";
    var CN_TWO = "贰";
    var CN_THREE = "叁";
    var CN_FOUR = "肆";
    var CN_FIVE = "伍";
    var CN_SIX = "陆";
    var CN_SEVEN = "柒";
    var CN_EIGHT = "捌";
    var CN_NINE = "玖";
    var CN_TEN = "拾";
    var CN_HUNDRED = "佰";
    var CN_THOUSAND = "仟";
    var CN_TEN_THOUSAND = "万";
    var CN_HUNDRED_MILLION = "亿";
    var CN_DOLLAR = "元";
    var CN_TEN_CENT = "角";
    var CN_CENT = "分";
    var CN_INTEGER = "整";

    // 初始化验证:
    var integral, decimal, outputCharacters, parts;
    var digits, radices, bigRadices, decimals;
    var zeroCount;
    var i, p, d;
    var quotient, modulus;
    //判断是否输入有效的数字格式
    var reg = /^((\d{1,3}(,\d{3})*(.((\d{3},)*\d{1,3}))?)|(\d+(.\d+)?))$/;
    // 验证输入字符串是否合法
    if (currencyDigits.toString() == "") {
        return "请输入购买金额";
    }

    if (!reg.test(currencyDigits)) {
        return "请输入有效格式数字";
    }

    currencyDigits = currencyDigits.replace(/,/g, "");
    currencyDigits = currencyDigits.replace(/^0+/, "");
    //判断输入的数字是否大于定义的数值
    if (Number(currencyDigits) > MAXIMUM_NUMBER) {
        return "您输入的数字太大了";
    }

    parts = currencyDigits.split(".");
    if (parts.length > 1) {
        integral = parts[0];
        decimal = parts[1];
        decimal = decimal.substr(0, 2);
    }
    else {
        integral = parts[0];
        decimal = "";
    }
    // 实例化字符大写人民币汉字对应的数字
    digits = new Array(CN_ZERO, CN_ONE, CN_TWO, CN_THREE, CN_FOUR, CN_FIVE, CN_SIX, CN_SEVEN, CN_EIGHT, CN_NINE);
    radices = new Array("", CN_TEN, CN_HUNDRED, CN_THOUSAND);
    bigRadices = new Array("", CN_TEN_THOUSAND, CN_HUNDRED_MILLION);
    decimals = new Array(CN_TEN_CENT, CN_CENT);

    outputCharacters = "";
    //大于零处理逻辑
    if (Number(integral) > 0) {
        zeroCount = 0;
        for (i = 0; i < integral.length; i++) {
            p = integral.length - i - 1;
            d = integral.substr(i, 1);
            quotient = p / 4;
            modulus = p % 4;
            if (d == "0") {
                zeroCount++;
            }
            else {
                if (zeroCount > 0) {
                    outputCharacters += digits[0];
                }
                zeroCount = 0;
                outputCharacters += digits[Number(d)] + radices[modulus];
            }
            if (modulus == 0 && zeroCount < 4) {
                outputCharacters += bigRadices[quotient];
            }
        }
        outputCharacters += CN_DOLLAR;
    }
    // 包含小数部分处理逻辑
    if (decimal != "") {
        for (i = 0; i < decimal.length; i++) {
            d = decimal.substr(i, 1);
            if (d != "0") {
                outputCharacters += digits[Number(d)] + decimals[i];
            }
        }
    }
    //确认并返回最终的输出字符串
    if (outputCharacters == "") {
        outputCharacters = CN_ZERO + CN_DOLLAR;
    }
    if (decimal == "") {
        outputCharacters += CN_INTEGER;
    }

    outputCharacters = outputCharacters.replace( /零分|零角|零拾|零佰|零仟/g, "零");
    outputCharacters = outputCharacters.replace( /零+/g, "零");
    outputCharacters = outputCharacters.replace( /零亿/g, "亿");
    outputCharacters = outputCharacters.replace( /零万/g, "万");
    outputCharacters = outputCharacters.replace( /零元/g, "元");
    outputCharacters = outputCharacters.replace( /亿万/g, "亿");
    outputCharacters = outputCharacters.replace( /^壹拾/, "拾");
    outputCharacters = outputCharacters.replace( /零$/, "整");

    //获取人民币大写
    return outputCharacters;
}

function isIdCardNo(num) {
    //if (isNaN(num)) {alert("输入的不是数字！"); return false;
    var len = num.length,
        re;
    if (len == 15) re = new RegExp(/^(\d{6})()?(\d{2})(\d{2})(\d{2})(\d{2})(\w)$/);
    else if (len == 18) re = new RegExp(/^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\w)$/);
    else {
        //alert("输入的数字位数不对。");
        return false;
    }
    var a = num.match(re);
    if (a != null) {
        if (len == 15) {
            var D = new Date("19" + a[3] + "/" + a[4] + "/" + a[5]);
            var B = D.getYear() == a[3] && (D.getMonth() + 1) == a[4] && D.getDate() == a[5];
        } else {
            var D = new Date(a[3] + "/" + a[4] + "/" + a[5]);
            var B = D.getFullYear() == a[3] && (D.getMonth() + 1) == a[4] && D.getDate() == a[5];
        }
        if (!B) {
            //alert("输入的身份证号 "+ a[0] +" 里出生日期不对。");
            return false;
        }
    }
    if (!re.test(num)) {
        //alert("身份证最后一位只能是数字和字母。");
        return false;
    }
    return true;
}

function isMobileNo(mobileNo) {
    var mobile = /^(((13[0-9]{1})|(14[5,7,9]{1})|(15[0-9]{1})|(17[0,5,6,7,8]{1})|(18[0-9]{1}))+\d{8})$/;
    var length = mobileNo.length;
    if (length == 11 && mobile.test(mobileNo)) {
        return true;
    }else{
        return false;
    }
}

function validBankCard(number) {
    var length = number.length;
    if(length<15 || length>19){
        return false;
    }else{
        return true;
    }
    /*var lastNum=number.substr(number.length-1,1);//取出最后一位（与luhm进行比较）

    var first15Num=number.substr(0,number.length-1);//前15或18位
    var newArr=new Array();
    for(var i=first15Num.length-1;i>-1;i--){    //前15或18位倒序存进数组
        newArr.push(first15Num.substr(i,1));
    }
    var arrJiShu=new Array();  //奇数位*2的积 <9
    var arrJiShu2=new Array(); //奇数位*2的积 >9

    var arrOuShu=new Array();  //偶数位数组
    for(var j=0;j<newArr.length;j++){
        if((j+1)%2==1){//奇数位
            if(parseInt(newArr[j])*2<9)
                arrJiShu.push(parseInt(newArr[j])*2);
            else
                arrJiShu2.push(parseInt(newArr[j])*2);
        }
        else //偶数位
            arrOuShu.push(newArr[j]);
    }

    var jishu_child1=new Array();//奇数位*2 >9 的分割之后的数组个位数
    var jishu_child2=new Array();//奇数位*2 >9 的分割之后的数组十位数
    for(var h=0;h<arrJiShu2.length;h++){
        jishu_child1.push(parseInt(arrJiShu2[h])%10);
        jishu_child2.push(parseInt(arrJiShu2[h])/10);
    }

    var sumJiShu=0; //奇数位*2 < 9 的数组之和
    var sumOuShu=0; //偶数位数组之和
    var sumJiShuChild1=0; //奇数位*2 >9 的分割之后的数组个位数之和
    var sumJiShuChild2=0; //奇数位*2 >9 的分割之后的数组十位数之和
    var sumTotal=0;
    for(var m=0;m<arrJiShu.length;m++){
        sumJiShu=sumJiShu+parseInt(arrJiShu[m]);
    }

    for(var n=0;n<arrOuShu.length;n++){
        sumOuShu=sumOuShu+parseInt(arrOuShu[n]);
    }

    for(var p=0;p<jishu_child1.length;p++){
        sumJiShuChild1=sumJiShuChild1+parseInt(jishu_child1[p]);
        sumJiShuChild2=sumJiShuChild2+parseInt(jishu_child2[p]);
    }
    //计算总和
    sumTotal=parseInt(sumJiShu)+parseInt(sumOuShu)+parseInt(sumJiShuChild1)+parseInt(sumJiShuChild2);

    //计算Luhm值
    var k= parseInt(sumTotal)%10==0?10:parseInt(sumTotal)%10;
    var luhm= 10-k;

    return lastNum==luhm;*/
}

function validLoginPassword(password){
    return /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/.test(password);
}
function isWeiXin() {
    var ua = window.navigator.userAgent.toLowerCase();
    if (ua.match(/MicroMessenger/i) == 'micromessenger') {
        return true;
    } else {
        return false;
    }
}
/**
 * 两种调用方式
 * var template1="我是{0}，今年{1}了";
 * var template2="我是{name}，今年{age}了";
 * var result1=template1.format("loogn",22);
 * var result2=template1.format({name:"loogn",age:22});
 * 两个结果都是"我是loogn，今年22了"
 **/
String.prototype.format = function(args) {
    if (arguments.length>0) {
        var result = this;
        if (arguments.length == 1 && typeof (args) == "object") {
            for (var key in args) {
                var reg=new RegExp ("({"+key+"})","g");
                result = result.replace(reg, args[key]);
            }
        }
        else {
            for (var i = 0; i < arguments.length; i++) {
                if(arguments[i]==undefined)
                {
                    return "";
                }
                else
                {
                    var reg=new RegExp ("({["+i+"]})","g");
                    result = result.replace(reg, arguments[i]);
                }
            }
        }
        return result;
    }
    else {
        return this;
    }
};
function isPayPassword(value) {
    var threeTheSame = -1;
    var num = 1;
    for (var i = 1; i < value.length; i++) {
        if(value[i-1] == value[i]) {
            num = num + 1;
        } else {
            num = 1;
        }
        if(num >= 3) {
            threeTheSame = 1;
            break;
        }
    }

    var obj = [];
    for (var i = 0; i < value.length; i++) {
        obj[i] = value.charCodeAt(i);
    }

    var allContinuous = 1;
    if(obj[0] = obj[1] - 1) {
        for (var i = 2; i < value.length; i++) {
            if(obj[i-1] != obj[i] - 1) {
                allContinuous = -1;
                break;
            }
        }
    } else if(obj[0] = obj[1] + 1) {
        for (var i = 2; i < value.length; i++) {
            if(obj[i-1] != obj[i] + 1) {
                allContinuous = -1;
                break;
            }
        }
    }

    return allContinuous == -1 && threeTheSame == -1;
}