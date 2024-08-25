package com.alrussy.order_service.model.enums;

public enum States {
	
	CREATED,//when create order by costumer
	ORDER_PROSSECING,//when come by in store
	DELIVERY,// when shipping 
	DELIVERY_AWAY,// this when order start from store to costumer   
	DELIVERED// end delivery

}
