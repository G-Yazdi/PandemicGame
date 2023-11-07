module com.yazdi.pandemic.playercontext {
    exports com.yazdi.pandemic.playercontext.model;
    exports com.yazdi.pandemic.playercontext.service;
    exports com.yazdi.pandemic.playercontext.repository;
	requires com.yazdi.pandemic.sharedkernel;
	provides com.yazdi.pandemic.playercontext.service.IPlayerService
    with com.yazdi.pandemic.playercontext.service.PlayerService;
    
}