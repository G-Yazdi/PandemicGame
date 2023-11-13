module com.yazdi.pandemic.api {
    uses com.yazdi.pandemic.sharedkernel.events.EventBus;
    uses com.yazdi.pandemic.playercontext.service.IPlayerService;
    uses com.yazdi.pandemic.playercontext.repository.PlayerRepository;
    uses com.yazdi.pandemic.mapcontext.service.IMapService;
    uses com.yazdi.pandemic.mapcontext.repository.MapRepository;
    requires transitive com.yazdi.pandemic.infrastructure;
	requires transitive com.yazdi.pandemic.playercontext;
	requires transitive com.yazdi.pandemic.sharedkernel;
	requires transitive com.yazdi.pandemic.mapcontext;
}