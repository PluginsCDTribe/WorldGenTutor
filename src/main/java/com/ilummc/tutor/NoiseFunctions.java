package com.ilummc.tutor;

import org.bukkit.util.noise.PerlinNoiseGenerator;
import org.bukkit.util.noise.SimplexNoiseGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import java.util.Random;

public class NoiseFunctions {

    /**
     * 假设此函数返回 0-1 之间的随机数，并且满足噪波函数的相关定义
     *
     * @param x 参数
     * @return 0-1 的随机数
     */
    static double noise(double x) {
        return PerlinNoiseGenerator.getNoise(x);
    }

    /**
     * 噪波函数
     *
     * @param x    参数
     * @param freq 频率
     * @param amp  振幅
     * @return 函数值
     */
    static double f(double x, double freq, double amp) {
        return amp * noise(x * freq);
    }

    static double f(double x, double freq, double amp, int octaves, boolean normalized) {
        double result = 0.0D;
        double a = 1.0D;
        double f = 1.0D;
        double max = 0.0D;

        for (int i = 0; i < octaves; ++i) {
            result += f(x, f, a);
            max += amp;
            f *= freq;
            a *= amp;
        }

        if (normalized) {
            result /= max;
        }

        return result;
    }

    static double bukkitNoise(double x, double freq, double amp, int octaves, boolean normalized) {
        SimplexNoiseGenerator generator = new SimplexNoiseGenerator(new Random());
        return generator.noise(x, octaves, freq, amp, normalized);
    }

    static double bukkitOctave(double x, double freq, double amp, int octaves, boolean normalized) {
        SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(), octaves);
        return generator.noise(x, freq, amp, normalized);
    }

}
