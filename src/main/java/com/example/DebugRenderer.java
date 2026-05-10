package com.example;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Box;
import org.joml.Matrix4f;

public class DebugRenderer {
    public static void drawBox(MatrixStack matrices, Box box, float r, float g, float b, float a) {
        Matrix4f matrix = matrices.peek().getPositionMatrix();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();

        RenderSystem.setShader(GameRenderer::getPositionColorProgram);
        buffer.begin(VertexFormat.DrawMode.DEBUG_LINES, VertexFormats.POSITION_COLOR);

        buffer.vertex(matrix, (float)box.minX, (float)box.minY, (float)box.minZ).color(r, g, b, a).next();
        buffer.vertex(matrix, (float)box.maxX, (float)box.minY, (float)box.minZ).color(r, g, b, a).next();
        
        tessellator.draw();
    }
}