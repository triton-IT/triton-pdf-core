package com.web4enterprise.pdf.core.font;

import com.web4enterprise.pdf.core.BoundingBox;

public class TimesRomanBold extends FontVariant {
	public static final String NAME = "Times-Roman_Bold";
	
	public String getName() {
		return NAME;
	}
	
	static {
		widths.put((byte) 32, 250); //space
		boxes.put((byte) 32, new BoundingBox(125, 0, 125, 0)); //space
		widths.put((byte) 33, 333); //exclam
		boxes.put((byte) 33, new BoundingBox(81, -13, 251, 691)); //exclam
		widths.put((byte) 34, 555); //quotedbl
		boxes.put((byte) 34, new BoundingBox(83, 404, 472, 691)); //quotedbl
		widths.put((byte) 35, 500); //numbersign
		boxes.put((byte) 35, new BoundingBox(4, 0, 496, 700)); //numbersign
		widths.put((byte) 36, 500); //dollar
		boxes.put((byte) 36, new BoundingBox(29, -99, 472, 750)); //dollar
		widths.put((byte) 37, 1000); //percent
		boxes.put((byte) 37, new BoundingBox(124, -14, 877, 692)); //percent
		widths.put((byte) 38, 833); //ampersand
		boxes.put((byte) 38, new BoundingBox(62, -16, 787, 691)); //ampersand
		widths.put((byte) 39, 333); //quoteright
		boxes.put((byte) 39, new BoundingBox(79, 356, 263, 691)); //quoteright
		widths.put((byte) 40, 333); //parenleft
		boxes.put((byte) 40, new BoundingBox(46, -168, 306, 694)); //parenleft
		widths.put((byte) 41, 333); //parenright
		boxes.put((byte) 41, new BoundingBox(27, -168, 287, 694)); //parenright
		widths.put((byte) 42, 500); //asterisk
		boxes.put((byte) 42, new BoundingBox(56, 255, 447, 691)); //asterisk
		widths.put((byte) 43, 570); //plus
		boxes.put((byte) 43, new BoundingBox(33, 0, 537, 506)); //plus
		widths.put((byte) 44, 250); //comma
		boxes.put((byte) 44, new BoundingBox(39, -180, 223, 155)); //comma
		widths.put((byte) 45, 333); //hyphen
		boxes.put((byte) 45, new BoundingBox(44, 171, 287, 287)); //hyphen
		widths.put((byte) 46, 250); //period
		boxes.put((byte) 46, new BoundingBox(41, -13, 210, 156)); //period
		widths.put((byte) 47, 278); //slash
		boxes.put((byte) 47, new BoundingBox(-24, -19, 302, 691)); //slash
		widths.put((byte) 48, 500); //zero
		boxes.put((byte) 48, new BoundingBox(24, -13, 476, 688)); //zero
		widths.put((byte) 49, 500); //one
		boxes.put((byte) 49, new BoundingBox(65, 0, 442, 688)); //one
		widths.put((byte) 50, 500); //two
		boxes.put((byte) 50, new BoundingBox(17, 0, 478, 688)); //two
		widths.put((byte) 51, 500); //three
		boxes.put((byte) 51, new BoundingBox(16, -14, 468, 688)); //three
		widths.put((byte) 52, 500); //four
		boxes.put((byte) 52, new BoundingBox(19, 0, 475, 688)); //four
		widths.put((byte) 53, 500); //five
		boxes.put((byte) 53, new BoundingBox(22, -8, 470, 676)); //five
		widths.put((byte) 54, 500); //six
		boxes.put((byte) 54, new BoundingBox(28, -13, 475, 688)); //six
		widths.put((byte) 55, 500); //seven
		boxes.put((byte) 55, new BoundingBox(17, 0, 477, 676)); //seven
		widths.put((byte) 56, 500); //eight
		boxes.put((byte) 56, new BoundingBox(28, -13, 472, 688)); //eight
		widths.put((byte) 57, 500); //nine
		boxes.put((byte) 57, new BoundingBox(26, -13, 473, 688)); //nine
		widths.put((byte) 58, 333); //colon
		boxes.put((byte) 58, new BoundingBox(82, -13, 251, 472)); //colon
		widths.put((byte) 59, 333); //semicolon
		boxes.put((byte) 59, new BoundingBox(82, -180, 266, 472)); //semicolon
		widths.put((byte) 60, 570); //less
		boxes.put((byte) 60, new BoundingBox(31, -12, 539, 518)); //less
		widths.put((byte) 61, 570); //equal
		boxes.put((byte) 61, new BoundingBox(33, 107, 537, 399)); //equal
		widths.put((byte) 62, 570); //greater
		boxes.put((byte) 62, new BoundingBox(31, -12, 539, 518)); //greater
		widths.put((byte) 63, 500); //question
		boxes.put((byte) 63, new BoundingBox(57, -13, 445, 689)); //question
		widths.put((byte) 64, 930); //at
		boxes.put((byte) 64, new BoundingBox(108, -19, 822, 691)); //at
		widths.put((byte) 65, 722); //A
		boxes.put((byte) 65, new BoundingBox(9, 0, 689, 690)); //A
		widths.put((byte) 66, 667); //B
		boxes.put((byte) 66, new BoundingBox(16, 0, 619, 676)); //B
		widths.put((byte) 67, 722); //C
		boxes.put((byte) 67, new BoundingBox(49, -19, 687, 691)); //C
		widths.put((byte) 68, 722); //D
		boxes.put((byte) 68, new BoundingBox(14, 0, 690, 676)); //D
		widths.put((byte) 69, 667); //E
		boxes.put((byte) 69, new BoundingBox(16, 0, 641, 676)); //E
		widths.put((byte) 70, 611); //F
		boxes.put((byte) 70, new BoundingBox(16, 0, 583, 676)); //F
		widths.put((byte) 71, 778); //G
		boxes.put((byte) 71, new BoundingBox(37, -19, 755, 691)); //G
		widths.put((byte) 72, 778); //H
		boxes.put((byte) 72, new BoundingBox(21, 0, 759, 676)); //H
		widths.put((byte) 73, 389); //I
		boxes.put((byte) 73, new BoundingBox(20, 0, 370, 676)); //I
		widths.put((byte) 74, 500); //J
		boxes.put((byte) 74, new BoundingBox(3, -96, 479, 676)); //J
		widths.put((byte) 75, 778); //K
		boxes.put((byte) 75, new BoundingBox(30, 0, 769, 676)); //K
		widths.put((byte) 76, 667); //L
		boxes.put((byte) 76, new BoundingBox(19, 0, 638, 676)); //L
		widths.put((byte) 77, 944); //M
		boxes.put((byte) 77, new BoundingBox(14, 0, 921, 676)); //M
		widths.put((byte) 78, 722); //N
		boxes.put((byte) 78, new BoundingBox(16, -18, 701, 676)); //N
		widths.put((byte) 79, 778); //O
		boxes.put((byte) 79, new BoundingBox(35, -19, 743, 691)); //O
		widths.put((byte) 80, 611); //P
		boxes.put((byte) 80, new BoundingBox(16, 0, 600, 676)); //P
		widths.put((byte) 81, 778); //Q
		boxes.put((byte) 81, new BoundingBox(35, -176, 743, 691)); //Q
		widths.put((byte) 82, 722); //R
		boxes.put((byte) 82, new BoundingBox(26, 0, 715, 676)); //R
		widths.put((byte) 83, 556); //S
		boxes.put((byte) 83, new BoundingBox(35, -19, 513, 692)); //S
		widths.put((byte) 84, 667); //T
		boxes.put((byte) 84, new BoundingBox(31, 0, 636, 676)); //T
		widths.put((byte) 85, 722); //U
		boxes.put((byte) 85, new BoundingBox(16, -19, 701, 676)); //U
		widths.put((byte) 86, 722); //V
		boxes.put((byte) 86, new BoundingBox(16, -18, 701, 676)); //V
		widths.put((byte) 87, 1000); //W
		boxes.put((byte) 87, new BoundingBox(19, -15, 981, 676)); //W
		widths.put((byte) 88, 722); //X
		boxes.put((byte) 88, new BoundingBox(16, 0, 699, 676)); //X
		widths.put((byte) 89, 722); //Y
		boxes.put((byte) 89, new BoundingBox(15, 0, 699, 676)); //Y
		widths.put((byte) 90, 667); //Z
		boxes.put((byte) 90, new BoundingBox(28, 0, 634, 676)); //Z
		widths.put((byte) 91, 333); //bracketleft
		boxes.put((byte) 91, new BoundingBox(67, -149, 301, 678)); //bracketleft
		widths.put((byte) 92, 278); //backslash
		boxes.put((byte) 92, new BoundingBox(-25, -19, 303, 691)); //backslash
		widths.put((byte) 93, 333); //bracketright
		boxes.put((byte) 93, new BoundingBox(32, -149, 266, 678)); //bracketright
		widths.put((byte) 94, 581); //asciicircum
		boxes.put((byte) 94, new BoundingBox(73, 311, 509, 676)); //asciicircum
		widths.put((byte) 95, 500); //underscore
		boxes.put((byte) 95, new BoundingBox(0, -125, 500, -75)); //underscore
		widths.put((byte) 96, 333); //quoteleft
		boxes.put((byte) 96, new BoundingBox(70, 356, 254, 691)); //quoteleft
		widths.put((byte) 97, 500); //a
		boxes.put((byte) 97, new BoundingBox(25, -14, 488, 473)); //a
		widths.put((byte) 98, 556); //b
		boxes.put((byte) 98, new BoundingBox(17, -14, 521, 676)); //b
		widths.put((byte) 99, 444); //c
		boxes.put((byte) 99, new BoundingBox(25, -14, 430, 473)); //c
		widths.put((byte) 100, 556); //d
		boxes.put((byte) 100, new BoundingBox(25, -14, 534, 676)); //d
		widths.put((byte) 101, 444); //e
		boxes.put((byte) 101, new BoundingBox(25, -14, 426, 473)); //e
		widths.put((byte) 102, 333); //f
		boxes.put((byte) 102, new BoundingBox(14, 0, 389, 691)); //f
		widths.put((byte) 103, 500); //g
		boxes.put((byte) 103, new BoundingBox(28, -206, 483, 473)); //g
		widths.put((byte) 104, 556); //h
		boxes.put((byte) 104, new BoundingBox(16, 0, 534, 676)); //h
		widths.put((byte) 105, 278); //i
		boxes.put((byte) 105, new BoundingBox(16, 0, 255, 691)); //i
		widths.put((byte) 106, 333); //j
		boxes.put((byte) 106, new BoundingBox(-57, -203, 263, 691)); //j
		widths.put((byte) 107, 556); //k
		boxes.put((byte) 107, new BoundingBox(22, 0, 543, 676)); //k
		widths.put((byte) 108, 278); //l
		boxes.put((byte) 108, new BoundingBox(16, 0, 255, 676)); //l
		widths.put((byte) 109, 833); //m
		boxes.put((byte) 109, new BoundingBox(16, 0, 814, 473)); //m
		widths.put((byte) 110, 556); //n
		boxes.put((byte) 110, new BoundingBox(21, 0, 539, 473)); //n
		widths.put((byte) 111, 500); //o
		boxes.put((byte) 111, new BoundingBox(25, -14, 476, 473)); //o
		widths.put((byte) 112, 556); //p
		boxes.put((byte) 112, new BoundingBox(19, -205, 524, 473)); //p
		widths.put((byte) 113, 556); //q
		boxes.put((byte) 113, new BoundingBox(34, -205, 536, 473)); //q
		widths.put((byte) 114, 444); //r
		boxes.put((byte) 114, new BoundingBox(29, 0, 434, 473)); //r
		widths.put((byte) 115, 389); //s
		boxes.put((byte) 115, new BoundingBox(25, -14, 361, 473)); //s
		widths.put((byte) 116, 333); //t
		boxes.put((byte) 116, new BoundingBox(20, -12, 332, 630)); //t
		widths.put((byte) 117, 556); //u
		boxes.put((byte) 117, new BoundingBox(16, -14, 537, 461)); //u
		widths.put((byte) 118, 500); //v
		boxes.put((byte) 118, new BoundingBox(21, -14, 485, 461)); //v
		widths.put((byte) 119, 722); //w
		boxes.put((byte) 119, new BoundingBox(23, -14, 707, 461)); //w
		widths.put((byte) 120, 500); //x
		boxes.put((byte) 120, new BoundingBox(12, 0, 484, 461)); //x
		widths.put((byte) 121, 500); //y
		boxes.put((byte) 121, new BoundingBox(16, -205, 480, 461)); //y
		widths.put((byte) 122, 444); //z
		boxes.put((byte) 122, new BoundingBox(21, 0, 420, 461)); //z
		widths.put((byte) 123, 394); //braceleft
		boxes.put((byte) 123, new BoundingBox(22, -175, 340, 698)); //braceleft
		widths.put((byte) 124, 220); //bar
		boxes.put((byte) 124, new BoundingBox(66, -19, 154, 691)); //bar
		widths.put((byte) 125, 394); //braceright
		boxes.put((byte) 125, new BoundingBox(54, -175, 372, 698)); //braceright
		widths.put((byte) 126, 520); //asciitilde
		boxes.put((byte) 126, new BoundingBox(29, 175, 491, 331)); //asciitilde
		widths.put((byte) 161, 333); //exclamdown
		boxes.put((byte) 161, new BoundingBox(82, -203, 252, 501)); //exclamdown
		widths.put((byte) 162, 500); //cent
		boxes.put((byte) 162, new BoundingBox(53, -140, 458, 588)); //cent
		widths.put((byte) 163, 500); //sterling
		boxes.put((byte) 163, new BoundingBox(21, -14, 477, 684)); //sterling
		widths.put((byte) 164, 167); //fraction
		boxes.put((byte) 164, new BoundingBox(-168, -12, 329, 688)); //fraction
		widths.put((byte) 165, 500); //yen
		boxes.put((byte) 165, new BoundingBox(-64, 0, 547, 676)); //yen
		widths.put((byte) 166, 500); //florin
		boxes.put((byte) 166, new BoundingBox(0, -155, 498, 706)); //florin
		widths.put((byte) 167, 500); //section
		boxes.put((byte) 167, new BoundingBox(57, -132, 443, 691)); //section
		widths.put((byte) 168, 500); //currency
		boxes.put((byte) 168, new BoundingBox(-26, 61, 526, 613)); //currency
		widths.put((byte) 169, 278); //quotesingle
		boxes.put((byte) 169, new BoundingBox(75, 404, 204, 691)); //quotesingle
		widths.put((byte) 170, 500); //quotedblleft
		boxes.put((byte) 170, new BoundingBox(32, 356, 486, 691)); //quotedblleft
		widths.put((byte) 171, 500); //guillemotleft
		boxes.put((byte) 171, new BoundingBox(23, 36, 473, 415)); //guillemotleft
		widths.put((byte) 172, 333); //guilsinglleft
		boxes.put((byte) 172, new BoundingBox(51, 36, 305, 415)); //guilsinglleft
		widths.put((byte) 173, 333); //guilsinglright
		boxes.put((byte) 173, new BoundingBox(28, 36, 282, 415)); //guilsinglright
		widths.put((byte) 174, 556); //fi
		boxes.put((byte) 174, new BoundingBox(14, 0, 536, 691)); //fi
		widths.put((byte) 175, 556); //fl
		boxes.put((byte) 175, new BoundingBox(14, 0, 536, 691)); //fl
		widths.put((byte) 177, 500); //endash
		boxes.put((byte) 177, new BoundingBox(0, 181, 500, 271)); //endash
		widths.put((byte) 178, 500); //dagger
		boxes.put((byte) 178, new BoundingBox(47, -134, 453, 691)); //dagger
		widths.put((byte) 179, 500); //daggerdbl
		boxes.put((byte) 179, new BoundingBox(45, -132, 456, 691)); //daggerdbl
		widths.put((byte) 180, 250); //periodcentered
		boxes.put((byte) 180, new BoundingBox(41, 248, 210, 417)); //periodcentered
		widths.put((byte) 182, 540); //paragraph
		boxes.put((byte) 182, new BoundingBox(0, -186, 519, 676)); //paragraph
		widths.put((byte) 183, 350); //bullet
		boxes.put((byte) 183, new BoundingBox(35, 198, 315, 478)); //bullet
		widths.put((byte) 184, 333); //quotesinglbase
		boxes.put((byte) 184, new BoundingBox(79, -180, 263, 155)); //quotesinglbase
		widths.put((byte) 185, 500); //quotedblbase
		boxes.put((byte) 185, new BoundingBox(14, -180, 468, 155)); //quotedblbase
		widths.put((byte) 186, 500); //quotedblright
		boxes.put((byte) 186, new BoundingBox(14, 356, 468, 691)); //quotedblright
		widths.put((byte) 187, 500); //guillemotright
		boxes.put((byte) 187, new BoundingBox(27, 36, 477, 415)); //guillemotright
		widths.put((byte) 188, 1000); //ellipsis
		boxes.put((byte) 188, new BoundingBox(82, -13, 917, 156)); //ellipsis
		widths.put((byte) 189, 1000); //perthousand
		boxes.put((byte) 189, new BoundingBox(7, -29, 995, 706)); //perthousand
		widths.put((byte) 191, 500); //questiondown
		boxes.put((byte) 191, new BoundingBox(55, -201, 443, 501)); //questiondown
		widths.put((byte) 193, 333); //grave
		boxes.put((byte) 193, new BoundingBox(8, 528, 246, 713)); //grave
		widths.put((byte) 194, 333); //acute
		boxes.put((byte) 194, new BoundingBox(86, 528, 324, 713)); //acute
		widths.put((byte) 195, 333); //circumflex
		boxes.put((byte) 195, new BoundingBox(-2, 528, 335, 704)); //circumflex
		widths.put((byte) 196, 333); //tilde
		boxes.put((byte) 196, new BoundingBox(-16, 547, 349, 674)); //tilde
		widths.put((byte) 197, 333); //macron
		boxes.put((byte) 197, new BoundingBox(1, 565, 331, 637)); //macron
		widths.put((byte) 198, 333); //breve
		boxes.put((byte) 198, new BoundingBox(15, 528, 318, 691)); //breve
		widths.put((byte) 199, 333); //dotaccent
		boxes.put((byte) 199, new BoundingBox(103, 537, 232, 666)); //dotaccent
		widths.put((byte) 200, 333); //dieresis
		boxes.put((byte) 200, new BoundingBox(-2, 537, 337, 666)); //dieresis
		widths.put((byte) 202, 333); //ring
		boxes.put((byte) 202, new BoundingBox(60, 537, 273, 750)); //ring
		widths.put((byte) 203, 333); //cedilla
		boxes.put((byte) 203, new BoundingBox(68, -218, 294, 0)); //cedilla
		widths.put((byte) 205, 333); //hungarumlaut
		boxes.put((byte) 205, new BoundingBox(-13, 528, 425, 713)); //hungarumlaut
		widths.put((byte) 206, 333); //ogonek
		boxes.put((byte) 206, new BoundingBox(90, -173, 319, 44)); //ogonek
		widths.put((byte) 207, 333); //caron
		boxes.put((byte) 207, new BoundingBox(-2, 528, 335, 704)); //caron
		widths.put((byte) 208, 1000); //emdash
		boxes.put((byte) 208, new BoundingBox(0, 181, 1000, 271)); //emdash
		widths.put((byte) 225, 1000); //AE
		boxes.put((byte) 225, new BoundingBox(4, 0, 951, 676)); //AE
		widths.put((byte) 227, 300); //ordfeminine
		boxes.put((byte) 227, new BoundingBox(-1, 397, 301, 688)); //ordfeminine
		widths.put((byte) 232, 667); //Lslash
		boxes.put((byte) 232, new BoundingBox(19, 0, 638, 676)); //Lslash
		widths.put((byte) 233, 778); //Oslash
		boxes.put((byte) 233, new BoundingBox(35, -74, 743, 737)); //Oslash
		widths.put((byte) 234, 1000); //OE
		boxes.put((byte) 234, new BoundingBox(22, -5, 981, 684)); //OE
		widths.put((byte) 235, 330); //ordmasculine
		boxes.put((byte) 235, new BoundingBox(18, 397, 312, 688)); //ordmasculine
		widths.put((byte) 241, 722); //ae
		boxes.put((byte) 241, new BoundingBox(33, -14, 693, 473)); //ae
		widths.put((byte) 245, 278); //dotlessi
		boxes.put((byte) 245, new BoundingBox(16, 0, 255, 461)); //dotlessi
		widths.put((byte) 248, 278); //lslash
		boxes.put((byte) 248, new BoundingBox(-22, 0, 303, 676)); //lslash
		widths.put((byte) 249, 500); //oslash
		boxes.put((byte) 249, new BoundingBox(25, -92, 476, 549)); //oslash
		widths.put((byte) 250, 722); //oe
		boxes.put((byte) 250, new BoundingBox(22, -14, 696, 473)); //oe
		widths.put((byte) 251, 556); //germandbls
		boxes.put((byte) 251, new BoundingBox(19, -12, 517, 691)); //germandbls
	}
}
