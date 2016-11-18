package com.web4enterprise.pdf.core.font;

import com.web4enterprise.pdf.core.BoundingBox;

public class TimesRomanBoldItalic extends FontVariant {
	public static final String NAME = "Times-Roman_Bold_Italic";
	
	public String getName() {
		return NAME;
	}
	
	static {
		widths.put((byte) 32, 250); //space
		boxes.put((byte) 32, new BoundingBox(125, 0, 125, 0)); //space
		widths.put((byte) 33, 389); //exclam
		boxes.put((byte) 33, new BoundingBox(67, -13, 370, 684)); //exclam
		widths.put((byte) 34, 555); //quotedbl
		boxes.put((byte) 34, new BoundingBox(136, 398, 536, 685)); //quotedbl
		widths.put((byte) 35, 500); //numbersign
		boxes.put((byte) 35, new BoundingBox(-33, 0, 533, 700)); //numbersign
		widths.put((byte) 36, 500); //dollar
		boxes.put((byte) 36, new BoundingBox(-20, -100, 497, 733)); //dollar
		widths.put((byte) 37, 833); //percent
		boxes.put((byte) 37, new BoundingBox(39, -10, 793, 692)); //percent
		widths.put((byte) 38, 778); //ampersand
		boxes.put((byte) 38, new BoundingBox(5, -19, 699, 682)); //ampersand
		widths.put((byte) 39, 333); //quoteright
		boxes.put((byte) 39, new BoundingBox(98, 369, 302, 685)); //quoteright
		widths.put((byte) 40, 333); //parenleft
		boxes.put((byte) 40, new BoundingBox(28, -179, 344, 685)); //parenleft
		widths.put((byte) 41, 333); //parenright
		boxes.put((byte) 41, new BoundingBox(-44, -179, 271, 685)); //parenright
		widths.put((byte) 42, 500); //asterisk
		boxes.put((byte) 42, new BoundingBox(65, 252, 456, 685)); //asterisk
		widths.put((byte) 43, 570); //plus
		boxes.put((byte) 43, new BoundingBox(33, 0, 537, 506)); //plus
		widths.put((byte) 44, 250); //comma
		boxes.put((byte) 44, new BoundingBox(-60, -182, 144, 134)); //comma
		widths.put((byte) 45, 333); //hyphen
		boxes.put((byte) 45, new BoundingBox(2, 166, 271, 282)); //hyphen
		widths.put((byte) 46, 250); //period
		boxes.put((byte) 46, new BoundingBox(-9, -13, 139, 135)); //period
		widths.put((byte) 47, 278); //slash
		boxes.put((byte) 47, new BoundingBox(-64, -18, 342, 685)); //slash
		widths.put((byte) 48, 500); //zero
		boxes.put((byte) 48, new BoundingBox(17, -14, 477, 683)); //zero
		widths.put((byte) 49, 500); //one
		boxes.put((byte) 49, new BoundingBox(5, 0, 419, 683)); //one
		widths.put((byte) 50, 500); //two
		boxes.put((byte) 50, new BoundingBox(-27, 0, 446, 683)); //two
		widths.put((byte) 51, 500); //three
		boxes.put((byte) 51, new BoundingBox(-15, -13, 450, 683)); //three
		widths.put((byte) 52, 500); //four
		boxes.put((byte) 52, new BoundingBox(-15, 0, 503, 683)); //four
		widths.put((byte) 53, 500); //five
		boxes.put((byte) 53, new BoundingBox(-11, -13, 487, 669)); //five
		widths.put((byte) 54, 500); //six
		boxes.put((byte) 54, new BoundingBox(23, -15, 509, 679)); //six
		widths.put((byte) 55, 500); //seven
		boxes.put((byte) 55, new BoundingBox(52, 0, 525, 669)); //seven
		widths.put((byte) 56, 500); //eight
		boxes.put((byte) 56, new BoundingBox(3, -13, 476, 683)); //eight
		widths.put((byte) 57, 500); //nine
		boxes.put((byte) 57, new BoundingBox(-12, -10, 475, 683)); //nine
		widths.put((byte) 58, 333); //colon
		boxes.put((byte) 58, new BoundingBox(23, -13, 264, 459)); //colon
		widths.put((byte) 59, 333); //semicolon
		boxes.put((byte) 59, new BoundingBox(-25, -183, 264, 459)); //semicolon
		widths.put((byte) 60, 570); //less
		boxes.put((byte) 60, new BoundingBox(31, -12, 539, 518)); //less
		widths.put((byte) 61, 570); //equal
		boxes.put((byte) 61, new BoundingBox(33, 107, 537, 399)); //equal
		widths.put((byte) 62, 570); //greater
		boxes.put((byte) 62, new BoundingBox(31, -12, 539, 518)); //greater
		widths.put((byte) 63, 500); //question
		boxes.put((byte) 63, new BoundingBox(79, -13, 470, 684)); //question
		widths.put((byte) 64, 832); //at
		boxes.put((byte) 64, new BoundingBox(63, -18, 770, 685)); //at
		widths.put((byte) 65, 667); //A
		boxes.put((byte) 65, new BoundingBox(-67, 0, 593, 683)); //A
		widths.put((byte) 66, 667); //B
		boxes.put((byte) 66, new BoundingBox(-24, 0, 624, 669)); //B
		widths.put((byte) 67, 667); //C
		boxes.put((byte) 67, new BoundingBox(32, -18, 677, 685)); //C
		widths.put((byte) 68, 722); //D
		boxes.put((byte) 68, new BoundingBox(-46, 0, 685, 669)); //D
		widths.put((byte) 69, 667); //E
		boxes.put((byte) 69, new BoundingBox(-27, 0, 653, 669)); //E
		widths.put((byte) 70, 667); //F
		boxes.put((byte) 70, new BoundingBox(-13, 0, 660, 669)); //F
		widths.put((byte) 71, 722); //G
		boxes.put((byte) 71, new BoundingBox(21, -18, 706, 685)); //G
		widths.put((byte) 72, 778); //H
		boxes.put((byte) 72, new BoundingBox(-24, 0, 799, 669)); //H
		widths.put((byte) 73, 389); //I
		boxes.put((byte) 73, new BoundingBox(-32, 0, 406, 669)); //I
		widths.put((byte) 74, 500); //J
		boxes.put((byte) 74, new BoundingBox(-46, -99, 524, 669)); //J
		widths.put((byte) 75, 667); //K
		boxes.put((byte) 75, new BoundingBox(-21, 0, 702, 669)); //K
		widths.put((byte) 76, 611); //L
		boxes.put((byte) 76, new BoundingBox(-22, 0, 590, 669)); //L
		widths.put((byte) 77, 889); //M
		boxes.put((byte) 77, new BoundingBox(-29, -12, 917, 669)); //M
		widths.put((byte) 78, 722); //N
		boxes.put((byte) 78, new BoundingBox(-27, -15, 748, 669)); //N
		widths.put((byte) 79, 722); //O
		boxes.put((byte) 79, new BoundingBox(27, -18, 691, 685)); //O
		widths.put((byte) 80, 611); //P
		boxes.put((byte) 80, new BoundingBox(-27, 0, 613, 669)); //P
		widths.put((byte) 81, 722); //Q
		boxes.put((byte) 81, new BoundingBox(27, -208, 691, 685)); //Q
		widths.put((byte) 82, 667); //R
		boxes.put((byte) 82, new BoundingBox(-29, 0, 623, 669)); //R
		widths.put((byte) 83, 556); //S
		boxes.put((byte) 83, new BoundingBox(2, -18, 526, 685)); //S
		widths.put((byte) 84, 611); //T
		boxes.put((byte) 84, new BoundingBox(50, 0, 650, 669)); //T
		widths.put((byte) 85, 722); //U
		boxes.put((byte) 85, new BoundingBox(67, -18, 744, 669)); //U
		widths.put((byte) 86, 667); //V
		boxes.put((byte) 86, new BoundingBox(65, -18, 715, 669)); //V
		widths.put((byte) 87, 889); //W
		boxes.put((byte) 87, new BoundingBox(65, -18, 940, 669)); //W
		widths.put((byte) 88, 667); //X
		boxes.put((byte) 88, new BoundingBox(-24, 0, 694, 669)); //X
		widths.put((byte) 89, 611); //Y
		boxes.put((byte) 89, new BoundingBox(73, 0, 659, 669)); //Y
		widths.put((byte) 90, 611); //Z
		boxes.put((byte) 90, new BoundingBox(-11, 0, 590, 669)); //Z
		widths.put((byte) 91, 333); //bracketleft
		boxes.put((byte) 91, new BoundingBox(-37, -159, 362, 674)); //bracketleft
		widths.put((byte) 92, 278); //backslash
		boxes.put((byte) 92, new BoundingBox(-1, -18, 279, 685)); //backslash
		widths.put((byte) 93, 333); //bracketright
		boxes.put((byte) 93, new BoundingBox(-56, -157, 343, 674)); //bracketright
		widths.put((byte) 94, 570); //asciicircum
		boxes.put((byte) 94, new BoundingBox(67, 304, 503, 669)); //asciicircum
		widths.put((byte) 95, 500); //underscore
		boxes.put((byte) 95, new BoundingBox(0, -125, 500, -75)); //underscore
		widths.put((byte) 96, 333); //quoteleft
		boxes.put((byte) 96, new BoundingBox(128, 369, 332, 685)); //quoteleft
		widths.put((byte) 97, 500); //a
		boxes.put((byte) 97, new BoundingBox(-21, -14, 455, 462)); //a
		widths.put((byte) 98, 500); //b
		boxes.put((byte) 98, new BoundingBox(-14, -13, 444, 699)); //b
		widths.put((byte) 99, 444); //c
		boxes.put((byte) 99, new BoundingBox(-5, -13, 392, 462)); //c
		widths.put((byte) 100, 500); //d
		boxes.put((byte) 100, new BoundingBox(-21, -13, 517, 699)); //d
		widths.put((byte) 101, 444); //e
		boxes.put((byte) 101, new BoundingBox(5, -13, 398, 462)); //e
		widths.put((byte) 102, 333); //f
		boxes.put((byte) 102, new BoundingBox(-169, -205, 446, 698)); //f
		widths.put((byte) 103, 500); //g
		boxes.put((byte) 103, new BoundingBox(-52, -203, 478, 462)); //g
		widths.put((byte) 104, 556); //h
		boxes.put((byte) 104, new BoundingBox(-13, -9, 498, 699)); //h
		widths.put((byte) 105, 278); //i
		boxes.put((byte) 105, new BoundingBox(2, -9, 263, 685)); //i
		widths.put((byte) 106, 278); //j
		boxes.put((byte) 106, new BoundingBox(-189, -207, 279, 685)); //j
		widths.put((byte) 107, 500); //k
		boxes.put((byte) 107, new BoundingBox(-23, -8, 483, 699)); //k
		widths.put((byte) 108, 278); //l
		boxes.put((byte) 108, new BoundingBox(2, -9, 290, 699)); //l
		widths.put((byte) 109, 778); //m
		boxes.put((byte) 109, new BoundingBox(-14, -9, 722, 462)); //m
		widths.put((byte) 110, 556); //n
		boxes.put((byte) 110, new BoundingBox(-6, -9, 493, 462)); //n
		widths.put((byte) 111, 500); //o
		boxes.put((byte) 111, new BoundingBox(-3, -13, 441, 462)); //o
		widths.put((byte) 112, 500); //p
		boxes.put((byte) 112, new BoundingBox(-120, -205, 446, 462)); //p
		widths.put((byte) 113, 500); //q
		boxes.put((byte) 113, new BoundingBox(1, -205, 471, 462)); //q
		widths.put((byte) 114, 389); //r
		boxes.put((byte) 114, new BoundingBox(-21, 0, 389, 462)); //r
		widths.put((byte) 115, 389); //s
		boxes.put((byte) 115, new BoundingBox(-19, -13, 333, 462)); //s
		widths.put((byte) 116, 278); //t
		boxes.put((byte) 116, new BoundingBox(-11, -9, 281, 594)); //t
		widths.put((byte) 117, 556); //u
		boxes.put((byte) 117, new BoundingBox(15, -9, 492, 462)); //u
		widths.put((byte) 118, 444); //v
		boxes.put((byte) 118, new BoundingBox(16, -13, 401, 462)); //v
		widths.put((byte) 119, 667); //w
		boxes.put((byte) 119, new BoundingBox(16, -13, 614, 462)); //w
		widths.put((byte) 120, 500); //x
		boxes.put((byte) 120, new BoundingBox(-46, -13, 469, 462)); //x
		widths.put((byte) 121, 444); //y
		boxes.put((byte) 121, new BoundingBox(-94, -205, 392, 462)); //y
		widths.put((byte) 122, 389); //z
		boxes.put((byte) 122, new BoundingBox(-43, -78, 368, 449)); //z
		widths.put((byte) 123, 348); //braceleft
		boxes.put((byte) 123, new BoundingBox(5, -187, 436, 686)); //braceleft
		widths.put((byte) 124, 220); //bar
		boxes.put((byte) 124, new BoundingBox(66, -18, 154, 685)); //bar
		widths.put((byte) 125, 348); //braceright
		boxes.put((byte) 125, new BoundingBox(-129, -187, 302, 686)); //braceright
		widths.put((byte) 126, 570); //asciitilde
		boxes.put((byte) 126, new BoundingBox(54, 175, 516, 331)); //asciitilde
		widths.put((byte) 161, 389); //exclamdown
		boxes.put((byte) 161, new BoundingBox(19, -205, 320, 494)); //exclamdown
		widths.put((byte) 162, 500); //cent
		boxes.put((byte) 162, new BoundingBox(42, -143, 439, 576)); //cent
		widths.put((byte) 163, 500); //sterling
		boxes.put((byte) 163, new BoundingBox(-32, -12, 510, 683)); //sterling
		widths.put((byte) 164, 167); //fraction
		boxes.put((byte) 164, new BoundingBox(-169, -14, 324, 683)); //fraction
		widths.put((byte) 165, 500); //yen
		boxes.put((byte) 165, new BoundingBox(33, 0, 628, 669)); //yen
		widths.put((byte) 166, 500); //florin
		boxes.put((byte) 166, new BoundingBox(-87, -156, 537, 707)); //florin
		widths.put((byte) 167, 500); //section
		boxes.put((byte) 167, new BoundingBox(36, -143, 459, 685)); //section
		widths.put((byte) 168, 500); //currency
		boxes.put((byte) 168, new BoundingBox(-26, 34, 526, 586)); //currency
		widths.put((byte) 169, 278); //quotesingle
		boxes.put((byte) 169, new BoundingBox(128, 398, 268, 685)); //quotesingle
		widths.put((byte) 170, 500); //quotedblleft
		boxes.put((byte) 170, new BoundingBox(53, 369, 513, 685)); //quotedblleft
		widths.put((byte) 171, 500); //guillemotleft
		boxes.put((byte) 171, new BoundingBox(12, 32, 468, 415)); //guillemotleft
		widths.put((byte) 172, 333); //guilsinglleft
		boxes.put((byte) 172, new BoundingBox(32, 32, 303, 415)); //guilsinglleft
		widths.put((byte) 173, 333); //guilsinglright
		boxes.put((byte) 173, new BoundingBox(10, 32, 281, 415)); //guilsinglright
		widths.put((byte) 174, 556); //fi
		boxes.put((byte) 174, new BoundingBox(-188, -205, 514, 703)); //fi
		widths.put((byte) 175, 556); //fl
		boxes.put((byte) 175, new BoundingBox(-186, -205, 553, 704)); //fl
		widths.put((byte) 177, 500); //endash
		boxes.put((byte) 177, new BoundingBox(-40, 178, 477, 269)); //endash
		widths.put((byte) 178, 500); //dagger
		boxes.put((byte) 178, new BoundingBox(91, -145, 494, 685)); //dagger
		widths.put((byte) 179, 500); //daggerdbl
		boxes.put((byte) 179, new BoundingBox(10, -139, 493, 685)); //daggerdbl
		widths.put((byte) 180, 250); //periodcentered
		boxes.put((byte) 180, new BoundingBox(51, 257, 199, 405)); //periodcentered
		widths.put((byte) 182, 500); //paragraph
		boxes.put((byte) 182, new BoundingBox(-57, -193, 562, 669)); //paragraph
		widths.put((byte) 183, 350); //bullet
		boxes.put((byte) 183, new BoundingBox(0, 175, 350, 525)); //bullet
		widths.put((byte) 184, 333); //quotesinglbase
		boxes.put((byte) 184, new BoundingBox(-5, -182, 199, 134)); //quotesinglbase
		widths.put((byte) 185, 500); //quotedblbase
		boxes.put((byte) 185, new BoundingBox(-57, -182, 403, 134)); //quotedblbase
		widths.put((byte) 186, 500); //quotedblright
		boxes.put((byte) 186, new BoundingBox(53, 369, 513, 685)); //quotedblright
		widths.put((byte) 187, 500); //guillemotright
		boxes.put((byte) 187, new BoundingBox(12, 32, 468, 415)); //guillemotright
		widths.put((byte) 188, 1000); //ellipsis
		boxes.put((byte) 188, new BoundingBox(40, -13, 852, 135)); //ellipsis
		widths.put((byte) 189, 1000); //perthousand
		boxes.put((byte) 189, new BoundingBox(7, -29, 996, 706)); //perthousand
		widths.put((byte) 191, 500); //questiondown
		boxes.put((byte) 191, new BoundingBox(30, -205, 421, 492)); //questiondown
		widths.put((byte) 193, 333); //grave
		boxes.put((byte) 193, new BoundingBox(85, 516, 297, 697)); //grave
		widths.put((byte) 194, 333); //acute
		boxes.put((byte) 194, new BoundingBox(139, 516, 379, 697)); //acute
		widths.put((byte) 195, 333); //circumflex
		boxes.put((byte) 195, new BoundingBox(40, 516, 367, 690)); //circumflex
		widths.put((byte) 196, 333); //tilde
		boxes.put((byte) 196, new BoundingBox(48, 536, 407, 655)); //tilde
		widths.put((byte) 197, 333); //macron
		boxes.put((byte) 197, new BoundingBox(51, 553, 393, 623)); //macron
		widths.put((byte) 198, 333); //breve
		boxes.put((byte) 198, new BoundingBox(71, 516, 387, 678)); //breve
		widths.put((byte) 199, 333); //dotaccent
		boxes.put((byte) 199, new BoundingBox(163, 525, 293, 655)); //dotaccent
		widths.put((byte) 200, 333); //dieresis
		boxes.put((byte) 200, new BoundingBox(55, 525, 397, 655)); //dieresis
		widths.put((byte) 202, 333); //ring
		boxes.put((byte) 202, new BoundingBox(127, 540, 340, 754)); //ring
		widths.put((byte) 203, 333); //cedilla
		boxes.put((byte) 203, new BoundingBox(-80, -218, 156, 5)); //cedilla
		widths.put((byte) 205, 333); //hungarumlaut
		boxes.put((byte) 205, new BoundingBox(69, 516, 498, 697)); //hungarumlaut
		widths.put((byte) 206, 333); //ogonek
		boxes.put((byte) 206, new BoundingBox(-40, -173, 189, 44)); //ogonek
		widths.put((byte) 207, 333); //caron
		boxes.put((byte) 207, new BoundingBox(79, 516, 411, 690)); //caron
		widths.put((byte) 208, 1000); //emdash
		boxes.put((byte) 208, new BoundingBox(-40, 178, 977, 269)); //emdash
		widths.put((byte) 225, 944); //AE
		boxes.put((byte) 225, new BoundingBox(-64, 0, 918, 669)); //AE
		widths.put((byte) 227, 266); //ordfeminine
		boxes.put((byte) 227, new BoundingBox(16, 399, 330, 685)); //ordfeminine
		widths.put((byte) 232, 611); //Lslash
		boxes.put((byte) 232, new BoundingBox(-22, 0, 590, 669)); //Lslash
		widths.put((byte) 233, 722); //Oslash
		boxes.put((byte) 233, new BoundingBox(27, -125, 691, 764)); //Oslash
		widths.put((byte) 234, 944); //OE
		boxes.put((byte) 234, new BoundingBox(23, -9, 946, 677)); //OE
		widths.put((byte) 235, 300); //ordmasculine
		boxes.put((byte) 235, new BoundingBox(56, 400, 350, 685)); //ordmasculine
		widths.put((byte) 241, 722); //ae
		boxes.put((byte) 241, new BoundingBox(-5, -13, 673, 462)); //ae
		widths.put((byte) 245, 278); //dotlessi
		boxes.put((byte) 245, new BoundingBox(2, -9, 238, 462)); //dotlessi
		widths.put((byte) 248, 278); //lslash
		boxes.put((byte) 248, new BoundingBox(-13, -9, 301, 699)); //lslash
		widths.put((byte) 249, 500); //oslash
		boxes.put((byte) 249, new BoundingBox(-3, -119, 441, 560)); //oslash
		widths.put((byte) 250, 722); //oe
		boxes.put((byte) 250, new BoundingBox(6, -13, 674, 462)); //oe
		widths.put((byte) 251, 500); //germandbls
		boxes.put((byte) 251, new BoundingBox(-200, -200, 473, 705)); //germandbls
	}
}
