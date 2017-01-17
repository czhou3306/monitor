App.filter("date_string",function(){
    return function(input){
        if(!input||!input.length||input.length<=10)
            return input;
        return input.substring(0,10);
    }
});