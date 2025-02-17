package thecodewarrior.hooked

import com.teamwizardry.librarianlib.features.base.ModCreativeTab
import com.teamwizardry.librarianlib.features.utilities.LoggerBase
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.launchwrapper.Launch
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import thecodewarrior.hooked.common.CommonProxy
import thecodewarrior.hooked.common.items.ModItems

@Mod(modid = HookedMod.MODID, version = HookedMod.VERSION, name = HookedMod.MODNAME, dependencies = HookedMod.DEPENDENCIES)
class HookedMod {

    @Mod.EventHandler
    fun preInit(e: FMLPreInitializationEvent) {
        PROXY.pre(e)
    }

    @Mod.EventHandler
    fun init(e: FMLInitializationEvent) {
        PROXY.init(e)
    }

    @Mod.EventHandler
    fun postInit(e: FMLPostInitializationEvent) {
        PROXY.post(e)
    }

    companion object {

        const val MODID = "hooked"
        const val MODNAME = "Hooked"
        const val VERSION = "%VERSION%"
        const val DEPENDENCIES = "required-after:librarianlib"
        const val CLIENT = "thecodewarrior.hooked.client.ClientProxy"
        const val SERVER = "thecodewarrior.hooked.common.CommonProxy"

        @JvmStatic
        @SidedProxy(clientSide = CLIENT, serverSide = SERVER)
        lateinit var PROXY: CommonProxy

        @JvmField
        val DEV_ENVIRONMENT = Launch.blackboard["fml.deobfuscatedEnvironment"] as Boolean

        val creativeTab = object : ModCreativeTab() {
            override fun createIcon(): ItemStack {
                return ItemStack(ModItems.hook)
            }

            override val iconStack = createIcon()

            init {
                this.registerDefaultTab()
            }
        }
    }

}

object HookLog : LoggerBase("Hooked")
