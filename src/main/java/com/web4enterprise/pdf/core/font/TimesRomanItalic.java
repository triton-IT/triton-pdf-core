package com.web4enterprise.pdf.core.font;

import com.web4enterprise.pdf.core.BoundingBox;

public class TimesRomanItalic extends FontVariant {
	public static final String NAME = "Times-Roman_Italic";
	
	public String getName() {
		return NAME;
	}
	
	static {
		widths.put((byte) 32, 250); //space
		boxes.put((byte) 32, new BoundingBox(125, 0, 125, 0)); //space
		widths.put((byte) 33, 333); //exclam
		boxes.put((byte) 33, new BoundingBox(39, -11, 302, 667)); //exclam
		widths.put((byte) 34, 420); //quotedbl
		boxes.put((byte) 34, new BoundingBox(144, 421, 432, 666)); //quotedbl
		widths.put((byte) 35, 500); //numbersign
		boxes.put((byte) 35, new BoundingBox(2, 0, 540, 676)); //numbersign
		widths.put((byte) 36, 500); //dollar
		boxes.put((byte) 36, new BoundingBox(31, -89, 497, 731)); //dollar
		widths.put((byte) 37, 833); //percent
		boxes.put((byte) 37, new BoundingBox(79, -13, 790, 676)); //percent
		widths.put((byte) 38, 778); //ampersand
		boxes.put((byte) 38, new BoundingBox(76, -18, 723, 666)); //ampersand
		widths.put((byte) 39, 333); //quoteright
		boxes.put((byte) 39, new BoundingBox(151, 436, 290, 666)); //quoteright
		widths.put((byte) 40, 333); //parenleft
		boxes.put((byte) 40, new BoundingBox(42, -181, 315, 669)); //parenleft
		widths.put((byte) 41, 333); //parenright
		boxes.put((byte) 41, new BoundingBox(16, -180, 289, 669)); //parenright
		widths.put((byte) 42, 500); //asterisk
		boxes.put((byte) 42, new BoundingBox(128, 255, 492, 666)); //asterisk
		widths.put((byte) 43, 675); //plus
		boxes.put((byte) 43, new BoundingBox(86, 0, 590, 506)); //plus
		widths.put((byte) 44, 250); //comma
		boxes.put((byte) 44, new BoundingBox(-4, -129, 135, 101)); //comma
		widths.put((byte) 45, 333); //hyphen
		boxes.put((byte) 45, new BoundingBox(49, 192, 282, 255)); //hyphen
		widths.put((byte) 46, 250); //period
		boxes.put((byte) 46, new BoundingBox(27, -11, 138, 100)); //period
		widths.put((byte) 47, 278); //slash
		boxes.put((byte) 47, new BoundingBox(-65, -18, 386, 666)); //slash
		widths.put((byte) 48, 500); //zero
		boxes.put((byte) 48, new BoundingBox(32, -7, 497, 676)); //zero
		widths.put((byte) 49, 500); //one
		boxes.put((byte) 49, new BoundingBox(49, 0, 409, 676)); //one
		widths.put((byte) 50, 500); //two
		boxes.put((byte) 50, new BoundingBox(12, 0, 452, 676)); //two
		widths.put((byte) 51, 500); //three
		boxes.put((byte) 51, new BoundingBox(15, -7, 466, 676)); //three
		widths.put((byte) 52, 500); //four
		boxes.put((byte) 52, new BoundingBox(1, 0, 479, 676)); //four
		widths.put((byte) 53, 500); //five
		boxes.put((byte) 53, new BoundingBox(15, -7, 491, 666)); //five
		widths.put((byte) 54, 500); //six
		boxes.put((byte) 54, new BoundingBox(30, -7, 521, 686)); //six
		widths.put((byte) 55, 500); //seven
		boxes.put((byte) 55, new BoundingBox(75, -8, 537, 666)); //seven
		widths.put((byte) 56, 500); //eight
		boxes.put((byte) 56, new BoundingBox(30, -7, 493, 676)); //eight
		widths.put((byte) 57, 500); //nine
		boxes.put((byte) 57, new BoundingBox(23, -17, 492, 676)); //nine
		widths.put((byte) 58, 333); //colon
		boxes.put((byte) 58, new BoundingBox(50, -11, 261, 441)); //colon
		widths.put((byte) 59, 333); //semicolon
		boxes.put((byte) 59, new BoundingBox(27, -129, 261, 441)); //semicolon
		widths.put((byte) 60, 675); //less
		boxes.put((byte) 60, new BoundingBox(84, -10, 592, 516)); //less
		widths.put((byte) 61, 675); //equal
		boxes.put((byte) 61, new BoundingBox(86, 120, 590, 386)); //equal
		widths.put((byte) 62, 675); //greater
		boxes.put((byte) 62, new BoundingBox(84, -10, 592, 516)); //greater
		widths.put((byte) 63, 500); //question
		boxes.put((byte) 63, new BoundingBox(132, -12, 472, 664)); //question
		widths.put((byte) 64, 920); //at
		boxes.put((byte) 64, new BoundingBox(118, -18, 806, 666)); //at
		widths.put((byte) 65, 611); //A
		boxes.put((byte) 65, new BoundingBox(-51, 0, 564, 668)); //A
		widths.put((byte) 66, 611); //B
		boxes.put((byte) 66, new BoundingBox(-8, 0, 588, 653)); //B
		widths.put((byte) 67, 667); //C
		boxes.put((byte) 67, new BoundingBox(66, -18, 689, 666)); //C
		widths.put((byte) 68, 722); //D
		boxes.put((byte) 68, new BoundingBox(-8, 0, 700, 653)); //D
		widths.put((byte) 69, 611); //E
		boxes.put((byte) 69, new BoundingBox(-1, 0, 634, 653)); //E
		widths.put((byte) 70, 611); //F
		boxes.put((byte) 70, new BoundingBox(8, 0, 645, 653)); //F
		widths.put((byte) 71, 722); //G
		boxes.put((byte) 71, new BoundingBox(52, -18, 722, 666)); //G
		widths.put((byte) 72, 722); //H
		boxes.put((byte) 72, new BoundingBox(-8, 0, 767, 653)); //H
		widths.put((byte) 73, 333); //I
		boxes.put((byte) 73, new BoundingBox(-8, 0, 384, 653)); //I
		widths.put((byte) 74, 444); //J
		boxes.put((byte) 74, new BoundingBox(-6, -18, 491, 653)); //J
		widths.put((byte) 75, 667); //K
		boxes.put((byte) 75, new BoundingBox(7, 0, 722, 653)); //K
		widths.put((byte) 76, 556); //L
		boxes.put((byte) 76, new BoundingBox(-8, 0, 559, 653)); //L
		widths.put((byte) 77, 833); //M
		boxes.put((byte) 77, new BoundingBox(-18, 0, 873, 653)); //M
		widths.put((byte) 78, 667); //N
		boxes.put((byte) 78, new BoundingBox(-20, -15, 727, 653)); //N
		widths.put((byte) 79, 722); //O
		boxes.put((byte) 79, new BoundingBox(60, -18, 706, 666)); //O
		widths.put((byte) 80, 611); //P
		boxes.put((byte) 80, new BoundingBox(0, 0, 605, 653)); //P
		widths.put((byte) 81, 722); //Q
		boxes.put((byte) 81, new BoundingBox(59, -183, 699, 666)); //Q
		widths.put((byte) 82, 611); //R
		boxes.put((byte) 82, new BoundingBox(-13, 0, 588, 653)); //R
		widths.put((byte) 83, 500); //S
		boxes.put((byte) 83, new BoundingBox(17, -18, 508, 667)); //S
		widths.put((byte) 84, 556); //T
		boxes.put((byte) 84, new BoundingBox(59, 0, 633, 653)); //T
		widths.put((byte) 85, 722); //U
		boxes.put((byte) 85, new BoundingBox(102, -18, 765, 653)); //U
		widths.put((byte) 86, 611); //V
		boxes.put((byte) 86, new BoundingBox(76, -18, 688, 653)); //V
		widths.put((byte) 87, 833); //W
		boxes.put((byte) 87, new BoundingBox(71, -18, 906, 653)); //W
		widths.put((byte) 88, 611); //X
		boxes.put((byte) 88, new BoundingBox(-29, 0, 655, 653)); //X
		widths.put((byte) 89, 556); //Y
		boxes.put((byte) 89, new BoundingBox(78, 0, 633, 653)); //Y
		widths.put((byte) 90, 556); //Z
		boxes.put((byte) 90, new BoundingBox(-6, 0, 606, 653)); //Z
		widths.put((byte) 91, 389); //bracketleft
		boxes.put((byte) 91, new BoundingBox(21, -153, 391, 663)); //bracketleft
		widths.put((byte) 92, 278); //backslash
		boxes.put((byte) 92, new BoundingBox(-41, -18, 319, 666)); //backslash
		widths.put((byte) 93, 389); //bracketright
		boxes.put((byte) 93, new BoundingBox(12, -153, 382, 663)); //bracketright
		widths.put((byte) 94, 422); //asciicircum
		boxes.put((byte) 94, new BoundingBox(0, 301, 422, 666)); //asciicircum
		widths.put((byte) 95, 500); //underscore
		boxes.put((byte) 95, new BoundingBox(0, -125, 500, -75)); //underscore
		widths.put((byte) 96, 333); //quoteleft
		boxes.put((byte) 96, new BoundingBox(171, 436, 310, 666)); //quoteleft
		widths.put((byte) 97, 500); //a
		boxes.put((byte) 97, new BoundingBox(17, -11, 476, 441)); //a
		widths.put((byte) 98, 500); //b
		boxes.put((byte) 98, new BoundingBox(23, -11, 473, 683)); //b
		widths.put((byte) 99, 444); //c
		boxes.put((byte) 99, new BoundingBox(30, -11, 425, 441)); //c
		widths.put((byte) 100, 500); //d
		boxes.put((byte) 100, new BoundingBox(15, -13, 527, 683)); //d
		widths.put((byte) 101, 444); //e
		boxes.put((byte) 101, new BoundingBox(31, -11, 412, 441)); //e
		widths.put((byte) 102, 278); //f
		boxes.put((byte) 102, new BoundingBox(-147, -207, 424, 678)); //f
		widths.put((byte) 103, 500); //g
		boxes.put((byte) 103, new BoundingBox(8, -206, 472, 441)); //g
		widths.put((byte) 104, 500); //h
		boxes.put((byte) 104, new BoundingBox(19, -9, 478, 683)); //h
		widths.put((byte) 105, 278); //i
		boxes.put((byte) 105, new BoundingBox(49, -11, 264, 654)); //i
		widths.put((byte) 106, 278); //j
		boxes.put((byte) 106, new BoundingBox(-124, -207, 276, 654)); //j
		widths.put((byte) 107, 444); //k
		boxes.put((byte) 107, new BoundingBox(14, -11, 461, 683)); //k
		widths.put((byte) 108, 278); //l
		boxes.put((byte) 108, new BoundingBox(40, -11, 279, 683)); //l
		widths.put((byte) 109, 722); //m
		boxes.put((byte) 109, new BoundingBox(12, -9, 704, 441)); //m
		widths.put((byte) 110, 500); //n
		boxes.put((byte) 110, new BoundingBox(14, -9, 474, 441)); //n
		widths.put((byte) 111, 500); //o
		boxes.put((byte) 111, new BoundingBox(27, -11, 468, 441)); //o
		widths.put((byte) 112, 500); //p
		boxes.put((byte) 112, new BoundingBox(-75, -205, 469, 442)); //p
		widths.put((byte) 113, 500); //q
		boxes.put((byte) 113, new BoundingBox(25, -209, 483, 441)); //q
		widths.put((byte) 114, 389); //r
		boxes.put((byte) 114, new BoundingBox(45, 0, 412, 441)); //r
		widths.put((byte) 115, 389); //s
		boxes.put((byte) 115, new BoundingBox(16, -13, 366, 442)); //s
		widths.put((byte) 116, 278); //t
		boxes.put((byte) 116, new BoundingBox(37, -11, 296, 546)); //t
		widths.put((byte) 117, 500); //u
		boxes.put((byte) 117, new BoundingBox(42, -11, 475, 441)); //u
		widths.put((byte) 118, 444); //v
		boxes.put((byte) 118, new BoundingBox(21, -18, 426, 441)); //v
		widths.put((byte) 119, 667); //w
		boxes.put((byte) 119, new BoundingBox(16, -18, 648, 441)); //w
		widths.put((byte) 120, 444); //x
		boxes.put((byte) 120, new BoundingBox(-27, -11, 447, 441)); //x
		widths.put((byte) 121, 444); //y
		boxes.put((byte) 121, new BoundingBox(-24, -206, 426, 441)); //y
		widths.put((byte) 122, 389); //z
		boxes.put((byte) 122, new BoundingBox(-2, -81, 380, 428)); //z
		widths.put((byte) 123, 400); //braceleft
		boxes.put((byte) 123, new BoundingBox(51, -177, 407, 687)); //braceleft
		widths.put((byte) 124, 275); //bar
		boxes.put((byte) 124, new BoundingBox(105, -18, 171, 666)); //bar
		widths.put((byte) 125, 400); //braceright
		boxes.put((byte) 125, new BoundingBox(-7, -177, 349, 687)); //braceright
		widths.put((byte) 126, 541); //asciitilde
		boxes.put((byte) 126, new BoundingBox(40, 186, 502, 320)); //asciitilde
		widths.put((byte) 161, 389); //exclamdown
		boxes.put((byte) 161, new BoundingBox(59, -205, 321, 474)); //exclamdown
		widths.put((byte) 162, 500); //cent
		boxes.put((byte) 162, new BoundingBox(77, -143, 472, 560)); //cent
		widths.put((byte) 163, 500); //sterling
		boxes.put((byte) 163, new BoundingBox(10, -6, 517, 670)); //sterling
		widths.put((byte) 164, 167); //fraction
		boxes.put((byte) 164, new BoundingBox(-169, -10, 337, 676)); //fraction
		widths.put((byte) 165, 500); //yen
		boxes.put((byte) 165, new BoundingBox(27, 0, 603, 653)); //yen
		widths.put((byte) 166, 500); //florin
		boxes.put((byte) 166, new BoundingBox(25, -182, 507, 682)); //florin
		widths.put((byte) 167, 500); //section
		boxes.put((byte) 167, new BoundingBox(53, -162, 461, 666)); //section
		widths.put((byte) 168, 500); //currency
		boxes.put((byte) 168, new BoundingBox(-22, 53, 522, 597)); //currency
		widths.put((byte) 169, 214); //quotesingle
		boxes.put((byte) 169, new BoundingBox(132, 421, 241, 666)); //quotesingle
		widths.put((byte) 170, 556); //quotedblleft
		boxes.put((byte) 170, new BoundingBox(166, 436, 514, 666)); //quotedblleft
		widths.put((byte) 171, 500); //guillemotleft
		boxes.put((byte) 171, new BoundingBox(53, 37, 445, 403)); //guillemotleft
		widths.put((byte) 172, 333); //guilsinglleft
		boxes.put((byte) 172, new BoundingBox(51, 37, 281, 403)); //guilsinglleft
		widths.put((byte) 173, 333); //guilsinglright
		boxes.put((byte) 173, new BoundingBox(52, 37, 282, 403)); //guilsinglright
		widths.put((byte) 174, 500); //fi
		boxes.put((byte) 174, new BoundingBox(-141, -207, 481, 681)); //fi
		widths.put((byte) 175, 500); //fl
		boxes.put((byte) 175, new BoundingBox(-141, -204, 518, 682)); //fl
		widths.put((byte) 177, 500); //endash
		boxes.put((byte) 177, new BoundingBox(-6, 197, 505, 243)); //endash
		widths.put((byte) 178, 500); //dagger
		boxes.put((byte) 178, new BoundingBox(101, -159, 488, 666)); //dagger
		widths.put((byte) 179, 500); //daggerdbl
		boxes.put((byte) 179, new BoundingBox(22, -143, 491, 666)); //daggerdbl
		widths.put((byte) 180, 250); //periodcentered
		boxes.put((byte) 180, new BoundingBox(70, 199, 181, 310)); //periodcentered
		widths.put((byte) 182, 523); //paragraph
		boxes.put((byte) 182, new BoundingBox(55, -123, 616, 653)); //paragraph
		widths.put((byte) 183, 350); //bullet
		boxes.put((byte) 183, new BoundingBox(40, 191, 310, 461)); //bullet
		widths.put((byte) 184, 333); //quotesinglbase
		boxes.put((byte) 184, new BoundingBox(44, -129, 183, 101)); //quotesinglbase
		widths.put((byte) 185, 556); //quotedblbase
		boxes.put((byte) 185, new BoundingBox(57, -129, 405, 101)); //quotedblbase
		widths.put((byte) 186, 556); //quotedblright
		boxes.put((byte) 186, new BoundingBox(151, 436, 499, 666)); //quotedblright
		widths.put((byte) 187, 500); //guillemotright
		boxes.put((byte) 187, new BoundingBox(55, 37, 447, 403)); //guillemotright
		widths.put((byte) 188, 889); //ellipsis
		boxes.put((byte) 188, new BoundingBox(57, -11, 762, 100)); //ellipsis
		widths.put((byte) 189, 1000); //perthousand
		boxes.put((byte) 189, new BoundingBox(25, -19, 1010, 706)); //perthousand
		widths.put((byte) 191, 500); //questiondown
		boxes.put((byte) 191, new BoundingBox(28, -205, 367, 473)); //questiondown
		widths.put((byte) 193, 333); //grave
		boxes.put((byte) 193, new BoundingBox(121, 492, 311, 664)); //grave
		widths.put((byte) 194, 333); //acute
		boxes.put((byte) 194, new BoundingBox(180, 494, 403, 664)); //acute
		widths.put((byte) 195, 333); //circumflex
		boxes.put((byte) 195, new BoundingBox(91, 492, 385, 661)); //circumflex
		widths.put((byte) 196, 333); //tilde
		boxes.put((byte) 196, new BoundingBox(100, 517, 427, 624)); //tilde
		widths.put((byte) 197, 333); //macron
		boxes.put((byte) 197, new BoundingBox(99, 532, 411, 583)); //macron
		widths.put((byte) 198, 333); //breve
		boxes.put((byte) 198, new BoundingBox(117, 492, 418, 650)); //breve
		widths.put((byte) 199, 333); //dotaccent
		boxes.put((byte) 199, new BoundingBox(207, 508, 305, 606)); //dotaccent
		widths.put((byte) 200, 333); //dieresis
		boxes.put((byte) 200, new BoundingBox(107, 508, 405, 606)); //dieresis
		widths.put((byte) 202, 333); //ring
		boxes.put((byte) 202, new BoundingBox(155, 508, 355, 707)); //ring
		widths.put((byte) 203, 333); //cedilla
		boxes.put((byte) 203, new BoundingBox(-30, -217, 182, 0)); //cedilla
		widths.put((byte) 205, 333); //hungarumlaut
		boxes.put((byte) 205, new BoundingBox(93, 494, 486, 664)); //hungarumlaut
		widths.put((byte) 206, 333); //ogonek
		boxes.put((byte) 206, new BoundingBox(-20, -169, 200, 40)); //ogonek
		widths.put((byte) 207, 333); //caron
		boxes.put((byte) 207, new BoundingBox(121, 492, 426, 661)); //caron
		widths.put((byte) 208, 889); //emdash
		boxes.put((byte) 208, new BoundingBox(-6, 197, 894, 243)); //emdash
		widths.put((byte) 225, 889); //AE
		boxes.put((byte) 225, new BoundingBox(-27, 0, 911, 653)); //AE
		widths.put((byte) 227, 276); //ordfeminine
		boxes.put((byte) 227, new BoundingBox(42, 406, 352, 676)); //ordfeminine
		widths.put((byte) 232, 556); //Lslash
		boxes.put((byte) 232, new BoundingBox(-8, 0, 559, 653)); //Lslash
		widths.put((byte) 233, 722); //Oslash
		boxes.put((byte) 233, new BoundingBox(60, -105, 699, 722)); //Oslash
		widths.put((byte) 234, 944); //OE
		boxes.put((byte) 234, new BoundingBox(49, -8, 964, 666)); //OE
		widths.put((byte) 235, 310); //ordmasculine
		boxes.put((byte) 235, new BoundingBox(67, 406, 362, 676)); //ordmasculine
		widths.put((byte) 241, 667); //ae
		boxes.put((byte) 241, new BoundingBox(23, -11, 640, 441)); //ae
		widths.put((byte) 245, 278); //dotlessi
		boxes.put((byte) 245, new BoundingBox(49, -11, 235, 441)); //dotlessi
		widths.put((byte) 248, 278); //lslash
		boxes.put((byte) 248, new BoundingBox(37, -11, 307, 683)); //lslash
		widths.put((byte) 249, 500); //oslash
		boxes.put((byte) 249, new BoundingBox(28, -135, 469, 554)); //oslash
		widths.put((byte) 250, 667); //oe
		boxes.put((byte) 250, new BoundingBox(20, -12, 646, 441)); //oe
		widths.put((byte) 251, 500); //germandbls
		boxes.put((byte) 251, new BoundingBox(-168, -207, 493, 679)); //germandbls
	}
}
