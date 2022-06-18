package me.sarim.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import me.sarim.FogCustomizer;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.FogShape;
import net.minecraft.client.render.BackgroundRenderer.FogType;

@Mixin(BackgroundRenderer.class)
public abstract class RendererMixin {
	@Inject(method = "applyFog", at = @At(value = "TAIL"))
	private static void applyCustomFog(Camera camera, FogType fogType, float viewDistance, boolean thickFog,
			float tickDelta, CallbackInfo ci) {

		FogShape fogShape = RenderSystem.getShaderFogShape();
		float fogStart = RenderSystem.getShaderFogStart();
		float fogEnd = RenderSystem.getShaderFogEnd();

		if (thickFog) {
			if (!FogCustomizer.CONFIG.Thick.override) {
				return;
			}
			fogStart = fogStart * FogCustomizer.CONFIG.Thick.fogStartMultiplier;
			fogEnd = fogEnd * FogCustomizer.CONFIG.Thick.fogEndMultiplier;
			fogShape = FogCustomizer.CONFIG.Thick.fogShape;
		} else {
			if (!FogCustomizer.CONFIG.Generic.override) {
				return;
			}
			fogStart = fogStart * FogCustomizer.CONFIG.Generic.fogStartMultiplier;
			fogEnd = fogEnd * FogCustomizer.CONFIG.Generic.fogEndMultiplier;
			fogShape = FogCustomizer.CONFIG.Generic.fogShape;
		}

		RenderSystem.setShaderFogStart(fogStart);
		RenderSystem.setShaderFogEnd(fogEnd);
		RenderSystem.setShaderFogShape(fogShape);
	}
}