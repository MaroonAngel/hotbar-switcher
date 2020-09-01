package net.maroonangel.hotbarswitcher;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;

public class HBS {

	public static KeyBinding switchKey = createKeyBinding(new Identifier("hotbarswitcher", "swap"), InputUtil.Type.KEYSYM, -1, "key.hotbarswitcher.category");

	public void init() {

	}

	private static KeyBinding createKeyBinding(Identifier id, InputUtil.Type type, int code, String category) {
		return KeyBindingHelper.registerKeyBinding(new KeyBinding("key." + id.getNamespace() + "." + id.getPath(), type, code, category));
	}
}
