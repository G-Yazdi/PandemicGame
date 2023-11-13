import com.yazdi.pandemic.infrastructure.db.InMemoryGameStore;
import com.yazdi.pandemic.infrastructure.events.SimpleEventBus;

module com.yazdi.pandemic.infrastructure {
    requires transitive com.yazdi.pandemic.sharedkernel;
    requires transitive com.yazdi.pandemic.playercontext;
    requires transitive com.yazdi.pandemic.mapcontext;
    provides com.yazdi.pandemic.sharedkernel.events.EventBus
      with SimpleEventBus;
    provides com.yazdi.pandemic.playercontext.repository.PlayerRepository
      with InMemoryGameStore;
    provides com.yazdi.pandemic.mapcontext.repository.MapRepository
    with InMemoryGameStore;
}