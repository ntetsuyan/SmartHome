/**
 * 
 */
 
 function fncConfirm(){
	
	if(window.confirm('本当に更新してよろしいですか？')){
		location.href = "/SmartHome/register4.html";
		return true;
		
	};
	return false;
}