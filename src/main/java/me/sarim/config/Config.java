package me.sarim.config;

import java.io.File;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.render.FogShape;

import java.io.IOException;

import me.sarim.FogCustomizer;

public class Config {

	private transient File file;

	public Category Generic = new Category(1F, 1F, FogShape.CYLINDER);
	public Category Thick = new Category(1F, 1F, FogShape.SPHERE);

	public class Category {
		public float fogStartMultiplier;
		public float fogEndMultiplier;
		public FogShape fogShape;
		public boolean override;

		public Category(float fogStartMultiplier, float fogEndMultiplier, FogShape fogShape) {
			this.fogStartMultiplier = fogStartMultiplier;
			this.fogEndMultiplier = fogEndMultiplier;
			this.fogShape = fogShape;
			this.override = false;
		}

	}

	public static Config load() {
		File file = new File(
				FabricLoader.getInstance().getConfigDir().toString(),
				FogCustomizer.MOD_ID + ".toml");

		Config config;
		if (file.exists()) {
			Toml configTOML = new Toml().read(file);
			config = configTOML.to(Config.class);
			config.file = file;
		} else {
			config = new Config();
			config.file = file;
			config.save();
		}
		return config;
	}

	public void save() {
		TomlWriter writer = new TomlWriter();
		try {
			writer.write(this, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
