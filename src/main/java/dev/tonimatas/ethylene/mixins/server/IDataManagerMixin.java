package dev.tonimatas.ethylene.mixins.server;

import dev.tonimatas.ethylene.interfaces.server.EthyleneIDataManager;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EthyleneIDataManager.class)
public interface IDataManagerMixin extends EthyleneIDataManager {
}
