module com.yazdi.pandemic.mapcontext {
    exports com.yazdi.pandemic.mapcontext.model;
    exports com.yazdi.pandemic.mapcontext.service;
    exports com.yazdi.pandemic.mapcontext.repository;
	requires com.yazdi.pandemic.sharedkernel;
	provides com.yazdi.pandemic.mapcontext.service.IMapService
    with com.yazdi.pandemic.mapcontext.service.MapService;
    
}