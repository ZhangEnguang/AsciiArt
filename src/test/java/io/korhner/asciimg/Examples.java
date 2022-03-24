package io.korhner.asciimg;

import io.korhner.asciimg.image.AsciiImgCache;
import io.korhner.asciimg.image.character_fit_strategy.BestCharacterFitStrategy;
import io.korhner.asciimg.image.character_fit_strategy.ColorSquareErrorFitStrategy;
import io.korhner.asciimg.image.character_fit_strategy.StructuralSimilarityFitStrategy;
import io.korhner.asciimg.image.converter.AsciiToImageConverter;
import io.korhner.asciimg.image.converter.AsciiToStringConverter;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Examples {

	public static void main(String[] args) throws IOException {

		// initialize caches（初始化缓存）
		AsciiImgCache smallFontCache = AsciiImgCache.create(new Font("Courier",
				Font.BOLD, 6));
		AsciiImgCache mediumBlackAndWhiteCache = AsciiImgCache.create(new Font(
				"Courier", Font.BOLD, 10), new char[] {'\\', ' ', '/'});
		AsciiImgCache largeFontCache = AsciiImgCache.create(new Font("Courier",
				Font.PLAIN, 16));

		// load image（加载的图片地址）
		BufferedImage portraitImage = ImageIO.read(new File(
				"examples/bear.png"));

		// initialize algorithms（初始化算法）
		BestCharacterFitStrategy squareErrorStrategy = new ColorSquareErrorFitStrategy();
		BestCharacterFitStrategy ssimStrategy = new StructuralSimilarityFitStrategy();

		// initialize converters
		AsciiToImageConverter imageConverter = new AsciiToImageConverter(
				smallFontCache, squareErrorStrategy);
		AsciiToStringConverter stringConverter = new AsciiToStringConverter(
				largeFontCache, ssimStrategy);

		// small font images, square error
		imageConverter.setCharacterCache(smallFontCache);
		imageConverter.setCharacterFitStrategy(squareErrorStrategy);
		ImageIO.write(imageConverter.convertImage(portraitImage), "png",
				new File("examples/bear_small_square_error.png"));

		// medium font images, square error
		imageConverter.setCharacterCache(mediumBlackAndWhiteCache);
		imageConverter.setCharacterFitStrategy(squareErrorStrategy);
		ImageIO.write(imageConverter.convertImage(portraitImage), "png",
				new File("examples/bear_medium_square_error.png"));

		// large font images, square error
		imageConverter.setCharacterCache(largeFontCache);
		imageConverter.setCharacterFitStrategy(squareErrorStrategy);
		ImageIO.write(imageConverter.convertImage(portraitImage), "png",
				new File("examples/bear_large_square_error.png"));

		// small font images, ssim
		imageConverter.setCharacterCache(smallFontCache);
		imageConverter.setCharacterFitStrategy(ssimStrategy);
		ImageIO.write(imageConverter.convertImage(portraitImage), "png",
				new File("examples/bear_small_ssim.png"));

		// medium font images, ssim error
		imageConverter.setCharacterCache(mediumBlackAndWhiteCache);
		imageConverter.setCharacterFitStrategy(ssimStrategy);
		ImageIO.write(imageConverter.convertImage(portraitImage), "png",
				new File("examples/bear_medium_ssim.png"));

		// large font images, ssim
		imageConverter.setCharacterCache(largeFontCache);
		imageConverter.setCharacterFitStrategy(ssimStrategy);
		ImageIO.write(imageConverter.convertImage(portraitImage), "png",
				new File("examples/bear_large_ssim.png"));

		// string converter, output to console
		System.out.println(stringConverter.convertImage(portraitImage));

	}
}
