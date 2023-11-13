module com.yazdi.pandemic.playercontext {
    exports com.yazdi.pandemic.playercontext.model.entities;
    exports com.yazdi.pandemic.playercontext.model.enums;
    exports com.yazdi.pandemic.playercontext.model.contracts;
    exports com.yazdi.pandemic.playercontext.service;
    exports com.yazdi.pandemic.playercontext.repository;
	requires com.yazdi.pandemic.sharedkernel;
	provides com.yazdi.pandemic.playercontext.service.IPlayerService
    with com.yazdi.pandemic.playercontext.service.PlayerService;
    
}