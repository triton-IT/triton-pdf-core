package com.web4enterprise.pdf.core.font;

import com.web4enterprise.pdf.core.BoundingBox;

/**
 * Used this regex to transform AFM to code: "^C (.*) ; WX (.*) ; N (.*) ; B (.*) (.*) (.*) (.*) ;" ==> "widths.put\(\(byte\) ${1}, ${2}\); //${3}\r\nboxes.put\(\(byte\) ${1}, new BoundingBox\(${4}, ${5}, ${6}, ${7}\)\); //${3}"
 * @author rramillien
 *
 */
public class TimesRoman extends Font {	
	static {
		widths.put((byte) 32, 250); //space
		boxes.put((byte) 32, new BoundingBox(0, 0, 0, 0)); //space
		widths.put((byte) 33, 333); //exclam
		boxes.put((byte) 33, new BoundingBox(130, -9, 238, 676)); //exclam
		widths.put((byte) 34, 408); //quotedbl
		boxes.put((byte) 34, new BoundingBox(77, 431, 331, 676)); //quotedbl
		widths.put((byte) 35, 500); //numbersign
		boxes.put((byte) 35, new BoundingBox(5, 0, 496, 662)); //numbersign
		widths.put((byte) 36, 500); //dollar
		boxes.put((byte) 36, new BoundingBox(44, -87, 457, 727)); //dollar
		widths.put((byte) 37, 833); //percent
		boxes.put((byte) 37, new BoundingBox(61, -13, 772, 676)); //percent
		widths.put((byte) 38, 778); //ampersand
		boxes.put((byte) 38, new BoundingBox(42, -13, 750, 676)); //ampersand
		widths.put((byte) 39, 333); //quoteright
		boxes.put((byte) 39, new BoundingBox(79, 433, 218, 676)); //quoteright
		widths.put((byte) 40, 333); //parenleft
		boxes.put((byte) 40, new BoundingBox(48, -177, 304, 676)); //parenleft
		widths.put((byte) 41, 333); //parenright
		boxes.put((byte) 41, new BoundingBox(29, -177, 285, 676)); //parenright
		widths.put((byte) 42, 500); //asterisk
		boxes.put((byte) 42, new BoundingBox(69, 265, 432, 676)); //asterisk
		widths.put((byte) 43, 564); //plus
		boxes.put((byte) 43, new BoundingBox(30, 0, 534, 506)); //plus
		widths.put((byte) 44, 250); //comma
		boxes.put((byte) 44, new BoundingBox(56, -141, 195, 102)); //comma
		widths.put((byte) 45, 333); //hyphen
		boxes.put((byte) 45, new BoundingBox(39, 194, 285, 257)); //hyphen
		widths.put((byte) 46, 250); //period
		boxes.put((byte) 46, new BoundingBox(70, -11, 181, 100)); //period
		widths.put((byte) 47, 278); //slash
		boxes.put((byte) 47, new BoundingBox(-9, -14, 287, 676)); //slash
		widths.put((byte) 48, 500); //zero
		boxes.put((byte) 48, new BoundingBox(24, -14, 476, 676)); //zero
		widths.put((byte) 49, 500); //one
		boxes.put((byte) 49, new BoundingBox(111, 0, 394, 676)); //one
		widths.put((byte) 50, 500); //two
		boxes.put((byte) 50, new BoundingBox(30, 0, 475, 676)); //two
		widths.put((byte) 51, 500); //three
		boxes.put((byte) 51, new BoundingBox(43, -14, 431, 676)); //three
		widths.put((byte) 52, 500); //four
		boxes.put((byte) 52, new BoundingBox(12, 0, 472, 676)); //four
		widths.put((byte) 53, 500); //five
		boxes.put((byte) 53, new BoundingBox(32, -14, 438, 688)); //five
		widths.put((byte) 54, 500); //six
		boxes.put((byte) 54, new BoundingBox(34, -14, 468, 684)); //six
		widths.put((byte) 55, 500); //seven
		boxes.put((byte) 55, new BoundingBox(20, -8, 449, 662)); //seven
		widths.put((byte) 56, 500); //eight
		boxes.put((byte) 56, new BoundingBox(56, -14, 445, 676)); //eight
		widths.put((byte) 57, 500); //nine
		boxes.put((byte) 57, new BoundingBox(30, -22, 459, 676)); //nine
		widths.put((byte) 58, 278); //colon
		boxes.put((byte) 58, new BoundingBox(81, -11, 192, 459)); //colon
		widths.put((byte) 59, 278); //semicolon
		boxes.put((byte) 59, new BoundingBox(80, -141, 219, 459)); //semicolon
		widths.put((byte) 60, 564); //less
		boxes.put((byte) 60, new BoundingBox(28, -8, 536, 514)); //less
		widths.put((byte) 61, 564); //equal
		boxes.put((byte) 61, new BoundingBox(30, 120, 534, 386)); //equal
		widths.put((byte) 62, 564); //greater
		boxes.put((byte) 62, new BoundingBox(28, -8, 536, 514)); //greater
		widths.put((byte) 63, 444); //question
		boxes.put((byte) 63, new BoundingBox(68, -8, 414, 676)); //question
		widths.put((byte) 64, 921); //at
		boxes.put((byte) 64, new BoundingBox(116, -14, 809, 676)); //at
		widths.put((byte) 65, 722); //A
		boxes.put((byte) 65, new BoundingBox(15, 0, 706, 674)); //A
		widths.put((byte) 66, 667); //B
		boxes.put((byte) 66, new BoundingBox(17, 0, 593, 662)); //B
		widths.put((byte) 67, 667); //C
		boxes.put((byte) 67, new BoundingBox(28, -14, 633, 676)); //C
		widths.put((byte) 68, 722); //D
		boxes.put((byte) 68, new BoundingBox(16, 0, 685, 662)); //D
		widths.put((byte) 69, 611); //E
		boxes.put((byte) 69, new BoundingBox(12, 0, 597, 662)); //E
		widths.put((byte) 70, 556); //F
		boxes.put((byte) 70, new BoundingBox(12, 0, 546, 662)); //F
		widths.put((byte) 71, 722); //G
		boxes.put((byte) 71, new BoundingBox(32, -14, 709, 676)); //G
		widths.put((byte) 72, 722); //H
		boxes.put((byte) 72, new BoundingBox(19, 0, 702, 662)); //H
		widths.put((byte) 73, 333); //I
		boxes.put((byte) 73, new BoundingBox(18, 0, 315, 662)); //I
		widths.put((byte) 74, 389); //J
		boxes.put((byte) 74, new BoundingBox(10, -14, 370, 662)); //J
		widths.put((byte) 75, 722); //K
		boxes.put((byte) 75, new BoundingBox(34, 0, 723, 662)); //K
		widths.put((byte) 76, 611); //L
		boxes.put((byte) 76, new BoundingBox(12, 0, 598, 662)); //L
		widths.put((byte) 77, 889); //M
		boxes.put((byte) 77, new BoundingBox(12, 0, 863, 662)); //M
		widths.put((byte) 78, 722); //N
		boxes.put((byte) 78, new BoundingBox(12, -11, 707, 662)); //N
		widths.put((byte) 79, 722); //O
		boxes.put((byte) 79, new BoundingBox(34, -14, 688, 676)); //O
		widths.put((byte) 80, 556); //P
		boxes.put((byte) 80, new BoundingBox(16, 0, 542, 662)); //P
		widths.put((byte) 81, 722); //Q
		boxes.put((byte) 81, new BoundingBox(34, -178, 701, 676)); //Q
		widths.put((byte) 82, 667); //R
		boxes.put((byte) 82, new BoundingBox(17, 0, 659, 662)); //R
		widths.put((byte) 83, 556); //S
		boxes.put((byte) 83, new BoundingBox(42, -14, 491, 676)); //S
		widths.put((byte) 84, 611); //T
		boxes.put((byte) 84, new BoundingBox(17, 0, 593, 662)); //T
		widths.put((byte) 85, 722); //U
		boxes.put((byte) 85, new BoundingBox(14, -14, 705, 662)); //U
		widths.put((byte) 86, 722); //V
		boxes.put((byte) 86, new BoundingBox(16, -11, 697, 662)); //V
		widths.put((byte) 87, 944); //W
		boxes.put((byte) 87, new BoundingBox(5, -11, 932, 662)); //W
		widths.put((byte) 88, 722); //X
		boxes.put((byte) 88, new BoundingBox(10, 0, 704, 662)); //X
		widths.put((byte) 89, 722); //Y
		boxes.put((byte) 89, new BoundingBox(22, 0, 703, 662)); //Y
		widths.put((byte) 90, 611); //Z
		boxes.put((byte) 90, new BoundingBox(9, 0, 597, 662)); //Z
		widths.put((byte) 91, 333); //bracketleft
		boxes.put((byte) 91, new BoundingBox(88, -156, 299, 662)); //bracketleft
		widths.put((byte) 92, 278); //backslash
		boxes.put((byte) 92, new BoundingBox(-9, -14, 287, 676)); //backslash
		widths.put((byte) 93, 333); //bracketright
		boxes.put((byte) 93, new BoundingBox(34, -156, 245, 662)); //bracketright
		widths.put((byte) 94, 469); //asciicircum
		boxes.put((byte) 94, new BoundingBox(24, 297, 446, 662)); //asciicircum
		widths.put((byte) 95, 500); //underscore
		boxes.put((byte) 95, new BoundingBox(0, -125, 500, -75)); //underscore
		widths.put((byte) 96, 333); //quoteleft
		boxes.put((byte) 96, new BoundingBox(115, 433, 254, 676)); //quoteleft
		widths.put((byte) 97, 444); //a
		boxes.put((byte) 97, new BoundingBox(37, -10, 442, 460)); //a
		widths.put((byte) 98, 500); //b
		boxes.put((byte) 98, new BoundingBox(3, -10, 468, 683)); //b
		widths.put((byte) 99, 444); //c
		boxes.put((byte) 99, new BoundingBox(25, -10, 412, 460)); //c
		widths.put((byte) 100, 500); //d
		boxes.put((byte) 100, new BoundingBox(27, -10, 491, 683)); //d
		widths.put((byte) 101, 444); //e
		boxes.put((byte) 101, new BoundingBox(25, -10, 424, 460)); //e
		widths.put((byte) 102, 333); //f
		boxes.put((byte) 102, new BoundingBox(20, 0, 383, 683)); //f
		widths.put((byte) 103, 500); //g
		boxes.put((byte) 103, new BoundingBox(28, -218, 470, 460)); //g
		widths.put((byte) 104, 500); //h
		boxes.put((byte) 104, new BoundingBox(9, 0, 487, 683)); //h
		widths.put((byte) 105, 278); //i
		boxes.put((byte) 105, new BoundingBox(16, 0, 253, 683)); //i
		widths.put((byte) 106, 278); //j
		boxes.put((byte) 106, new BoundingBox(-70, -218, 194, 683)); //j
		widths.put((byte) 107, 500); //k
		boxes.put((byte) 107, new BoundingBox(7, 0, 505, 683)); //k
		widths.put((byte) 108, 278); //l
		boxes.put((byte) 108, new BoundingBox(19, 0, 257, 683)); //l
		widths.put((byte) 109, 778); //m
		boxes.put((byte) 109, new BoundingBox(16, 0, 775, 460)); //m
		widths.put((byte) 110, 500); //n
		boxes.put((byte) 110, new BoundingBox(16, 0, 485, 460)); //n
		widths.put((byte) 111, 500); //o
		boxes.put((byte) 111, new BoundingBox(29, -10, 470, 460)); //o
		widths.put((byte) 112, 500); //p
		boxes.put((byte) 112, new BoundingBox(5, -217, 470, 460)); //p
		widths.put((byte) 113, 500); //q
		boxes.put((byte) 113, new BoundingBox(24, -217, 488, 460)); //q
		widths.put((byte) 114, 333); //r
		boxes.put((byte) 114, new BoundingBox(5, 0, 335, 460)); //r
		widths.put((byte) 115, 389); //s
		boxes.put((byte) 115, new BoundingBox(51, -10, 348, 460)); //s
		widths.put((byte) 116, 278); //t
		boxes.put((byte) 116, new BoundingBox(13, -10, 279, 579)); //t
		widths.put((byte) 117, 500); //u
		boxes.put((byte) 117, new BoundingBox(9, -10, 479, 450)); //u
		widths.put((byte) 118, 500); //v
		boxes.put((byte) 118, new BoundingBox(19, -14, 477, 450)); //v
		widths.put((byte) 119, 722); //w
		boxes.put((byte) 119, new BoundingBox(21, -14, 694, 450)); //w
		widths.put((byte) 120, 500); //x
		boxes.put((byte) 120, new BoundingBox(17, 0, 479, 450)); //x
		widths.put((byte) 121, 500); //y
		boxes.put((byte) 121, new BoundingBox(14, -218, 475, 450)); //y
		widths.put((byte) 122, 444); //z
		boxes.put((byte) 122, new BoundingBox(27, 0, 418, 450)); //z
		widths.put((byte) 123, 480); //braceleft
		boxes.put((byte) 123, new BoundingBox(100, -181, 350, 680)); //braceleft
		widths.put((byte) 124, 200); //bar
		boxes.put((byte) 124, new BoundingBox(67, -218, 133, 782)); //bar
		widths.put((byte) 125, 480); //braceright
		boxes.put((byte) 125, new BoundingBox(130, -181, 380, 680)); //braceright
		widths.put((byte) 126, 541); //asciitilde
		boxes.put((byte) 126, new BoundingBox(40, 183, 502, 323)); //asciitilde
		widths.put((byte) 161, 333); //exclamdown
		boxes.put((byte) 161, new BoundingBox(97, -218, 205, 467)); //exclamdown
		widths.put((byte) 162, 500); //cent
		boxes.put((byte) 162, new BoundingBox(53, -138, 448, 579)); //cent
		widths.put((byte) 163, 500); //sterling
		boxes.put((byte) 163, new BoundingBox(12, -8, 490, 676)); //sterling
		widths.put((byte) 164, 167); //fraction
		boxes.put((byte) 164, new BoundingBox(-168, -14, 331, 676)); //fraction
		widths.put((byte) 165, 500); //yen
		boxes.put((byte) 165, new BoundingBox(-53, 0, 512, 662)); //yen
		widths.put((byte) 166, 500); //florin
		boxes.put((byte) 166, new BoundingBox(7, -189, 490, 676)); //florin
		widths.put((byte) 167, 500); //section
		boxes.put((byte) 167, new BoundingBox(70, -148, 426, 676)); //section
		widths.put((byte) 168, 500); //currency
		boxes.put((byte) 168, new BoundingBox(-22, 58, 522, 602)); //currency
		widths.put((byte) 169, 180); //quotesingle
		boxes.put((byte) 169, new BoundingBox(48, 431, 133, 676)); //quotesingle
		widths.put((byte) 170, 444); //quotedblleft
		boxes.put((byte) 170, new BoundingBox(43, 433, 414, 676)); //quotedblleft
		widths.put((byte) 171, 500); //guillemotleft
		boxes.put((byte) 171, new BoundingBox(42, 33, 456, 416)); //guillemotleft
		widths.put((byte) 172, 333); //guilsinglleft
		boxes.put((byte) 172, new BoundingBox(63, 33, 285, 416)); //guilsinglleft
		widths.put((byte) 173, 333); //guilsinglright
		boxes.put((byte) 173, new BoundingBox(48, 33, 270, 416)); //guilsinglright
		widths.put((byte) 174, 556); //fi
		boxes.put((byte) 174, new BoundingBox(31, 0, 521, 683)); //fi
		widths.put((byte) 175, 556); //fl
		boxes.put((byte) 175, new BoundingBox(32, 0, 521, 683)); //fl
		widths.put((byte) 177, 500); //endash
		boxes.put((byte) 177, new BoundingBox(0, 201, 500, 250)); //endash
		widths.put((byte) 178, 500); //dagger
		boxes.put((byte) 178, new BoundingBox(59, -149, 442, 676)); //dagger
		widths.put((byte) 179, 500); //daggerdbl
		boxes.put((byte) 179, new BoundingBox(58, -153, 442, 676)); //daggerdbl
		widths.put((byte) 180, 250); //periodcentered
		boxes.put((byte) 180, new BoundingBox(70, 199, 181, 310)); //periodcentered
		widths.put((byte) 182, 453); //paragraph
		boxes.put((byte) 182, new BoundingBox(-22, -154, 450, 662)); //paragraph
		widths.put((byte) 183, 350); //bullet
		boxes.put((byte) 183, new BoundingBox(40, 196, 310, 466)); //bullet
		widths.put((byte) 184, 333); //quotesinglbase
		boxes.put((byte) 184, new BoundingBox(79, -141, 218, 102)); //quotesinglbase
		widths.put((byte) 185, 444); //quotedblbase
		boxes.put((byte) 185, new BoundingBox(45, -141, 416, 102)); //quotedblbase
		widths.put((byte) 186, 444); //quotedblright
		boxes.put((byte) 186, new BoundingBox(30, 433, 401, 676)); //quotedblright
		widths.put((byte) 187, 500); //guillemotright
		boxes.put((byte) 187, new BoundingBox(44, 33, 458, 416)); //guillemotright
		widths.put((byte) 188, 1000); //ellipsis
		boxes.put((byte) 188, new BoundingBox(111, -11, 888, 100)); //ellipsis
		widths.put((byte) 189, 1000); //perthousand
		boxes.put((byte) 189, new BoundingBox(7, -19, 994, 706)); //perthousand
		widths.put((byte) 191, 444); //questiondown
		boxes.put((byte) 191, new BoundingBox(30, -218, 376, 466)); //questiondown
		widths.put((byte) 193, 333); //grave
		boxes.put((byte) 193, new BoundingBox(19, 507, 242, 678)); //grave
		widths.put((byte) 194, 333); //acute
		boxes.put((byte) 194, new BoundingBox(93, 507, 317, 678)); //acute
		widths.put((byte) 195, 333); //circumflex
		boxes.put((byte) 195, new BoundingBox(11, 507, 322, 674)); //circumflex
		widths.put((byte) 196, 333); //tilde
		boxes.put((byte) 196, new BoundingBox(1, 532, 331, 638)); //tilde
		widths.put((byte) 197, 333); //macron
		boxes.put((byte) 197, new BoundingBox(11, 547, 322, 601)); //macron
		widths.put((byte) 198, 333); //breve
		boxes.put((byte) 198, new BoundingBox(26, 507, 307, 664)); //breve
		widths.put((byte) 199, 333); //dotaccent
		boxes.put((byte) 199, new BoundingBox(118, 581, 216, 681)); //dotaccent
		widths.put((byte) 200, 333); //dieresis
		boxes.put((byte) 200, new BoundingBox(18, 581, 315, 681)); //dieresis
		widths.put((byte) 202, 333); //ring
		boxes.put((byte) 202, new BoundingBox(67, 512, 266, 711)); //ring
		widths.put((byte) 203, 333); //cedilla
		boxes.put((byte) 203, new BoundingBox(52, -215, 261, 0)); //cedilla
		widths.put((byte) 205, 333); //hungarumlaut
		boxes.put((byte) 205, new BoundingBox(-3, 507, 377, 678)); //hungarumlaut
		widths.put((byte) 206, 333); //ogonek
		boxes.put((byte) 206, new BoundingBox(62, -165, 243, 0)); //ogonek
		widths.put((byte) 207, 333); //caron
		boxes.put((byte) 207, new BoundingBox(11, 507, 322, 674)); //caron
		widths.put((byte) 208, 1000); //emdash
		boxes.put((byte) 208, new BoundingBox(0, 201, 1000, 250)); //emdash
		widths.put((byte) 225, 889); //AE
		boxes.put((byte) 225, new BoundingBox(0, 0, 863, 662)); //AE
		widths.put((byte) 227, 276); //ordfeminine
		boxes.put((byte) 227, new BoundingBox(4, 394, 270, 676)); //ordfeminine
		widths.put((byte) 232, 611); //Lslash
		boxes.put((byte) 232, new BoundingBox(12, 0, 598, 662)); //Lslash
		widths.put((byte) 233, 722); //Oslash
		boxes.put((byte) 233, new BoundingBox(34, -80, 688, 734)); //Oslash
		widths.put((byte) 234, 889); //OE
		boxes.put((byte) 234, new BoundingBox(30, -6, 885, 668)); //OE
		widths.put((byte) 235, 310); //ordmasculine
		boxes.put((byte) 235, new BoundingBox(6, 394, 304, 676)); //ordmasculine
		widths.put((byte) 241, 667); //ae
		boxes.put((byte) 241, new BoundingBox(38, -10, 632, 460)); //ae
		widths.put((byte) 245, 278); //dotlessi
		boxes.put((byte) 245, new BoundingBox(16, 0, 253, 460)); //dotlessi
		widths.put((byte) 248, 278); //lslash
		boxes.put((byte) 248, new BoundingBox(19, 0, 259, 683)); //lslash
		widths.put((byte) 249, 500); //oslash
		boxes.put((byte) 249, new BoundingBox(29, -112, 470, 551)); //oslash
		widths.put((byte) 250, 722); //oe
		boxes.put((byte) 250, new BoundingBox(30, -10, 690, 460)); //oe
		widths.put((byte) 251, 500); //germandbls
		boxes.put((byte) 251, new BoundingBox(12, -9, 468, 683)); //germandbls
		widths.put((byte) -1, 333); //Idieresis
		boxes.put((byte) -1, new BoundingBox(18, 0, 315, 835)); //Idieresis
		widths.put((byte) -1, 444); //eacute
		boxes.put((byte) -1, new BoundingBox(25, -10, 424, 678)); //eacute
		widths.put((byte) -1, 444); //abreve
		boxes.put((byte) -1, new BoundingBox(37, -10, 442, 664)); //abreve
		widths.put((byte) -1, 500); //uhungarumlaut
		boxes.put((byte) -1, new BoundingBox(9, -10, 501, 678)); //uhungarumlaut
		widths.put((byte) -1, 444); //ecaron
		boxes.put((byte) -1, new BoundingBox(25, -10, 424, 674)); //ecaron
		widths.put((byte) -1, 722); //Ydieresis
		boxes.put((byte) -1, new BoundingBox(22, 0, 703, 835)); //Ydieresis
		widths.put((byte) -1, 564); //divide
		boxes.put((byte) -1, new BoundingBox(30, -10, 534, 516)); //divide
		widths.put((byte) -1, 722); //Yacute
		boxes.put((byte) -1, new BoundingBox(22, 0, 703, 890)); //Yacute
		widths.put((byte) -1, 722); //Acircumflex
		boxes.put((byte) -1, new BoundingBox(15, 0, 706, 886)); //Acircumflex
		widths.put((byte) -1, 444); //aacute
		boxes.put((byte) -1, new BoundingBox(37, -10, 442, 678)); //aacute
		widths.put((byte) -1, 722); //Ucircumflex
		boxes.put((byte) -1, new BoundingBox(14, -14, 705, 886)); //Ucircumflex
		widths.put((byte) -1, 500); //yacute
		boxes.put((byte) -1, new BoundingBox(14, -218, 475, 678)); //yacute
		widths.put((byte) -1, 389); //scommaaccent
		boxes.put((byte) -1, new BoundingBox(51, -218, 348, 460)); //scommaaccent
		widths.put((byte) -1, 444); //ecircumflex
		boxes.put((byte) -1, new BoundingBox(25, -10, 424, 674)); //ecircumflex
		widths.put((byte) -1, 722); //Uring
		boxes.put((byte) -1, new BoundingBox(14, -14, 705, 898)); //Uring
		widths.put((byte) -1, 722); //Udieresis
		boxes.put((byte) -1, new BoundingBox(14, -14, 705, 835)); //Udieresis
		widths.put((byte) -1, 444); //aogonek
		boxes.put((byte) -1, new BoundingBox(37, -165, 469, 460)); //aogonek
		widths.put((byte) -1, 722); //Uacute
		boxes.put((byte) -1, new BoundingBox(14, -14, 705, 890)); //Uacute
		widths.put((byte) -1, 500); //uogonek
		boxes.put((byte) -1, new BoundingBox(9, -155, 487, 450)); //uogonek
		widths.put((byte) -1, 611); //Edieresis
		boxes.put((byte) -1, new BoundingBox(12, 0, 597, 835)); //Edieresis
		widths.put((byte) -1, 722); //Dcroat
		boxes.put((byte) -1, new BoundingBox(16, 0, 685, 662)); //Dcroat
		widths.put((byte) -1, 250); //commaaccent
		boxes.put((byte) -1, new BoundingBox(59, -218, 184, -50)); //commaaccent
		widths.put((byte) -1, 760); //copyright
		boxes.put((byte) -1, new BoundingBox(38, -14, 722, 676)); //copyright
		widths.put((byte) -1, 611); //Emacron
		boxes.put((byte) -1, new BoundingBox(12, 0, 597, 813)); //Emacron
		widths.put((byte) -1, 444); //ccaron
		boxes.put((byte) -1, new BoundingBox(25, -10, 412, 674)); //ccaron
		widths.put((byte) -1, 444); //aring
		boxes.put((byte) -1, new BoundingBox(37, -10, 442, 711)); //aring
		widths.put((byte) -1, 722); //Ncommaaccent
		boxes.put((byte) -1, new BoundingBox(12, -198, 707, 662)); //Ncommaaccent
		widths.put((byte) -1, 278); //lacute
		boxes.put((byte) -1, new BoundingBox(19, 0, 290, 890)); //lacute
		widths.put((byte) -1, 444); //agrave
		boxes.put((byte) -1, new BoundingBox(37, -10, 442, 678)); //agrave
		widths.put((byte) -1, 611); //Tcommaaccent
		boxes.put((byte) -1, new BoundingBox(17, -218, 593, 662)); //Tcommaaccent
		widths.put((byte) -1, 667); //Cacute
		boxes.put((byte) -1, new BoundingBox(28, -14, 633, 890)); //Cacute
		widths.put((byte) -1, 444); //atilde
		boxes.put((byte) -1, new BoundingBox(37, -10, 442, 638)); //atilde
		widths.put((byte) -1, 611); //Edotaccent
		boxes.put((byte) -1, new BoundingBox(12, 0, 597, 835)); //Edotaccent
		widths.put((byte) -1, 389); //scaron
		boxes.put((byte) -1, new BoundingBox(39, -10, 350, 674)); //scaron
		widths.put((byte) -1, 389); //scedilla
		boxes.put((byte) -1, new BoundingBox(51, -215, 348, 460)); //scedilla
		widths.put((byte) -1, 278); //iacute
		boxes.put((byte) -1, new BoundingBox(16, 0, 290, 678)); //iacute
		widths.put((byte) -1, 471); //lozenge
		boxes.put((byte) -1, new BoundingBox(13, 0, 459, 724)); //lozenge
		widths.put((byte) -1, 667); //Rcaron
		boxes.put((byte) -1, new BoundingBox(17, 0, 659, 886)); //Rcaron
		widths.put((byte) -1, 722); //Gcommaaccent
		boxes.put((byte) -1, new BoundingBox(32, -218, 709, 676)); //Gcommaaccent
		widths.put((byte) -1, 500); //ucircumflex
		boxes.put((byte) -1, new BoundingBox(9, -10, 479, 674)); //ucircumflex
		widths.put((byte) -1, 444); //acircumflex
		boxes.put((byte) -1, new BoundingBox(37, -10, 442, 674)); //acircumflex
		widths.put((byte) -1, 722); //Amacron
		boxes.put((byte) -1, new BoundingBox(15, 0, 706, 813)); //Amacron
		widths.put((byte) -1, 333); //rcaron
		boxes.put((byte) -1, new BoundingBox(5, 0, 335, 674)); //rcaron
		widths.put((byte) -1, 444); //ccedilla
		boxes.put((byte) -1, new BoundingBox(25, -215, 412, 460)); //ccedilla
		widths.put((byte) -1, 611); //Zdotaccent
		boxes.put((byte) -1, new BoundingBox(9, 0, 597, 835)); //Zdotaccent
		widths.put((byte) -1, 556); //Thorn
		boxes.put((byte) -1, new BoundingBox(16, 0, 542, 662)); //Thorn
		widths.put((byte) -1, 722); //Omacron
		boxes.put((byte) -1, new BoundingBox(34, -14, 688, 813)); //Omacron
		widths.put((byte) -1, 667); //Racute
		boxes.put((byte) -1, new BoundingBox(17, 0, 659, 890)); //Racute
		widths.put((byte) -1, 556); //Sacute
		boxes.put((byte) -1, new BoundingBox(42, -14, 491, 890)); //Sacute
		widths.put((byte) -1, 588); //dcaron
		boxes.put((byte) -1, new BoundingBox(27, -10, 589, 695)); //dcaron
		widths.put((byte) -1, 722); //Umacron
		boxes.put((byte) -1, new BoundingBox(14, -14, 705, 813)); //Umacron
		widths.put((byte) -1, 500); //uring
		boxes.put((byte) -1, new BoundingBox(9, -10, 479, 711)); //uring
		widths.put((byte) -1, 300); //threesuperior
		boxes.put((byte) -1, new BoundingBox(15, 262, 291, 676)); //threesuperior
		widths.put((byte) -1, 722); //Ograve
		boxes.put((byte) -1, new BoundingBox(34, -14, 688, 890)); //Ograve
		widths.put((byte) -1, 722); //Agrave
		boxes.put((byte) -1, new BoundingBox(15, 0, 706, 890)); //Agrave
		widths.put((byte) -1, 722); //Abreve
		boxes.put((byte) -1, new BoundingBox(15, 0, 706, 876)); //Abreve
		widths.put((byte) -1, 564); //multiply
		boxes.put((byte) -1, new BoundingBox(38, 8, 527, 497)); //multiply
		widths.put((byte) -1, 500); //uacute
		boxes.put((byte) -1, new BoundingBox(9, -10, 479, 678)); //uacute
		widths.put((byte) -1, 611); //Tcaron
		boxes.put((byte) -1, new BoundingBox(17, 0, 593, 886)); //Tcaron
		widths.put((byte) -1, 476); //partialdiff
		boxes.put((byte) -1, new BoundingBox(17, -38, 459, 710)); //partialdiff
		widths.put((byte) -1, 500); //ydieresis
		boxes.put((byte) -1, new BoundingBox(14, -218, 475, 623)); //ydieresis
		widths.put((byte) -1, 722); //Nacute
		boxes.put((byte) -1, new BoundingBox(12, -11, 707, 890)); //Nacute
		widths.put((byte) -1, 278); //icircumflex
		boxes.put((byte) -1, new BoundingBox(-16, 0, 295, 674)); //icircumflex
		widths.put((byte) -1, 611); //Ecircumflex
		boxes.put((byte) -1, new BoundingBox(12, 0, 597, 886)); //Ecircumflex
		widths.put((byte) -1, 444); //adieresis
		boxes.put((byte) -1, new BoundingBox(37, -10, 442, 623)); //adieresis
		widths.put((byte) -1, 444); //edieresis
		boxes.put((byte) -1, new BoundingBox(25, -10, 424, 623)); //edieresis
		widths.put((byte) -1, 444); //cacute
		boxes.put((byte) -1, new BoundingBox(25, -10, 413, 678)); //cacute
		widths.put((byte) -1, 500); //nacute
		boxes.put((byte) -1, new BoundingBox(16, 0, 485, 678)); //nacute
		widths.put((byte) -1, 500); //umacron
		boxes.put((byte) -1, new BoundingBox(9, -10, 479, 601)); //umacron
		widths.put((byte) -1, 722); //Ncaron
		boxes.put((byte) -1, new BoundingBox(12, -11, 707, 886)); //Ncaron
		widths.put((byte) -1, 333); //Iacute
		boxes.put((byte) -1, new BoundingBox(18, 0, 317, 890)); //Iacute
		widths.put((byte) -1, 564); //plusminus
		boxes.put((byte) -1, new BoundingBox(30, 0, 534, 506)); //plusminus
		widths.put((byte) -1, 200); //brokenbar
		boxes.put((byte) -1, new BoundingBox(67, -143, 133, 707)); //brokenbar
		widths.put((byte) -1, 760); //registered
		boxes.put((byte) -1, new BoundingBox(38, -14, 722, 676)); //registered
		widths.put((byte) -1, 722); //Gbreve
		boxes.put((byte) -1, new BoundingBox(32, -14, 709, 876)); //Gbreve
		widths.put((byte) -1, 333); //Idotaccent
		boxes.put((byte) -1, new BoundingBox(18, 0, 315, 835)); //Idotaccent
		widths.put((byte) -1, 600); //summation
		boxes.put((byte) -1, new BoundingBox(15, -10, 585, 706)); //summation
		widths.put((byte) -1, 611); //Egrave
		boxes.put((byte) -1, new BoundingBox(12, 0, 597, 890)); //Egrave
		widths.put((byte) -1, 333); //racute
		boxes.put((byte) -1, new BoundingBox(5, 0, 335, 678)); //racute
		widths.put((byte) -1, 500); //omacron
		boxes.put((byte) -1, new BoundingBox(29, -10, 470, 601)); //omacron
		widths.put((byte) -1, 611); //Zacute
		boxes.put((byte) -1, new BoundingBox(9, 0, 597, 890)); //Zacute
		widths.put((byte) -1, 611); //Zcaron
		boxes.put((byte) -1, new BoundingBox(9, 0, 597, 886)); //Zcaron
		widths.put((byte) -1, 549); //greaterequal
		boxes.put((byte) -1, new BoundingBox(26, 0, 523, 666)); //greaterequal
		widths.put((byte) -1, 722); //Eth
		boxes.put((byte) -1, new BoundingBox(16, 0, 685, 662)); //Eth
		widths.put((byte) -1, 667); //Ccedilla
		boxes.put((byte) -1, new BoundingBox(28, -215, 633, 676)); //Ccedilla
		widths.put((byte) -1, 278); //lcommaaccent
		boxes.put((byte) -1, new BoundingBox(19, -218, 257, 683)); //lcommaaccent
		widths.put((byte) -1, 326); //tcaron
		boxes.put((byte) -1, new BoundingBox(13, -10, 318, 722)); //tcaron
		widths.put((byte) -1, 444); //eogonek
		boxes.put((byte) -1, new BoundingBox(25, -165, 424, 460)); //eogonek
		widths.put((byte) -1, 722); //Uogonek
		boxes.put((byte) -1, new BoundingBox(14, -165, 705, 662)); //Uogonek
		widths.put((byte) -1, 722); //Aacute
		boxes.put((byte) -1, new BoundingBox(15, 0, 706, 890)); //Aacute
		widths.put((byte) -1, 722); //Adieresis
		boxes.put((byte) -1, new BoundingBox(15, 0, 706, 835)); //Adieresis
		widths.put((byte) -1, 444); //egrave
		boxes.put((byte) -1, new BoundingBox(25, -10, 424, 678)); //egrave
		widths.put((byte) -1, 444); //zacute
		boxes.put((byte) -1, new BoundingBox(27, 0, 418, 678)); //zacute
		widths.put((byte) -1, 278); //iogonek
		boxes.put((byte) -1, new BoundingBox(16, -165, 265, 683)); //iogonek
		widths.put((byte) -1, 722); //Oacute
		boxes.put((byte) -1, new BoundingBox(34, -14, 688, 890)); //Oacute
		widths.put((byte) -1, 500); //oacute
		boxes.put((byte) -1, new BoundingBox(29, -10, 470, 678)); //oacute
		widths.put((byte) -1, 444); //amacron
		boxes.put((byte) -1, new BoundingBox(37, -10, 442, 601)); //amacron
		widths.put((byte) -1, 389); //sacute
		boxes.put((byte) -1, new BoundingBox(51, -10, 348, 678)); //sacute
		widths.put((byte) -1, 278); //idieresis
		boxes.put((byte) -1, new BoundingBox(-9, 0, 288, 623)); //idieresis
		widths.put((byte) -1, 722); //Ocircumflex
		boxes.put((byte) -1, new BoundingBox(34, -14, 688, 886)); //Ocircumflex
		widths.put((byte) -1, 722); //Ugrave
		boxes.put((byte) -1, new BoundingBox(14, -14, 705, 890)); //Ugrave
		widths.put((byte) -1, 612); //Delta
		boxes.put((byte) -1, new BoundingBox(6, 0, 608, 688)); //Delta
		widths.put((byte) -1, 500); //thorn
		boxes.put((byte) -1, new BoundingBox(5, -217, 470, 683)); //thorn
		widths.put((byte) -1, 300); //twosuperior
		boxes.put((byte) -1, new BoundingBox(1, 270, 296, 676)); //twosuperior
		widths.put((byte) -1, 722); //Odieresis
		boxes.put((byte) -1, new BoundingBox(34, -14, 688, 835)); //Odieresis
		widths.put((byte) -1, 500); //mu
		boxes.put((byte) -1, new BoundingBox(36, -218, 512, 450)); //mu
		widths.put((byte) -1, 278); //igrave
		boxes.put((byte) -1, new BoundingBox(-8, 0, 253, 678)); //igrave
		widths.put((byte) -1, 500); //ohungarumlaut
		boxes.put((byte) -1, new BoundingBox(29, -10, 491, 678)); //ohungarumlaut
		widths.put((byte) -1, 611); //Eogonek
		boxes.put((byte) -1, new BoundingBox(12, -165, 597, 662)); //Eogonek
		widths.put((byte) -1, 500); //dcroat
		boxes.put((byte) -1, new BoundingBox(27, -10, 500, 683)); //dcroat
		widths.put((byte) -1, 750); //threequarters
		boxes.put((byte) -1, new BoundingBox(15, -14, 718, 676)); //threequarters
		widths.put((byte) -1, 556); //Scedilla
		boxes.put((byte) -1, new BoundingBox(42, -215, 491, 676)); //Scedilla
		widths.put((byte) -1, 344); //lcaron
		boxes.put((byte) -1, new BoundingBox(19, 0, 347, 695)); //lcaron
		widths.put((byte) -1, 722); //Kcommaaccent
		boxes.put((byte) -1, new BoundingBox(34, -198, 723, 662)); //Kcommaaccent
		widths.put((byte) -1, 611); //Lacute
		boxes.put((byte) -1, new BoundingBox(12, 0, 598, 890)); //Lacute
		widths.put((byte) -1, 980); //trademark
		boxes.put((byte) -1, new BoundingBox(30, 256, 957, 662)); //trademark
		widths.put((byte) -1, 444); //edotaccent
		boxes.put((byte) -1, new BoundingBox(25, -10, 424, 623)); //edotaccent
		widths.put((byte) -1, 333); //Igrave
		boxes.put((byte) -1, new BoundingBox(18, 0, 315, 890)); //Igrave
		widths.put((byte) -1, 333); //Imacron
		boxes.put((byte) -1, new BoundingBox(11, 0, 322, 813)); //Imacron
		widths.put((byte) -1, 611); //Lcaron
		boxes.put((byte) -1, new BoundingBox(12, 0, 598, 676)); //Lcaron
		widths.put((byte) -1, 750); //onehalf
		boxes.put((byte) -1, new BoundingBox(31, -14, 746, 676)); //onehalf
		widths.put((byte) -1, 549); //lessequal
		boxes.put((byte) -1, new BoundingBox(26, 0, 523, 666)); //lessequal
		widths.put((byte) -1, 500); //ocircumflex
		boxes.put((byte) -1, new BoundingBox(29, -10, 470, 674)); //ocircumflex
		widths.put((byte) -1, 500); //ntilde
		boxes.put((byte) -1, new BoundingBox(16, 0, 485, 638)); //ntilde
		widths.put((byte) -1, 722); //Uhungarumlaut
		boxes.put((byte) -1, new BoundingBox(14, -14, 705, 890)); //Uhungarumlaut
		widths.put((byte) -1, 611); //Eacute
		boxes.put((byte) -1, new BoundingBox(12, 0, 597, 890)); //Eacute
		widths.put((byte) -1, 444); //emacron
		boxes.put((byte) -1, new BoundingBox(25, -10, 424, 601)); //emacron
		widths.put((byte) -1, 500); //gbreve
		boxes.put((byte) -1, new BoundingBox(28, -218, 470, 664)); //gbreve
		widths.put((byte) -1, 750); //onequarter
		boxes.put((byte) -1, new BoundingBox(37, -14, 718, 676)); //onequarter
		widths.put((byte) -1, 556); //Scaron
		boxes.put((byte) -1, new BoundingBox(42, -14, 491, 886)); //Scaron
		widths.put((byte) -1, 556); //Scommaaccent
		boxes.put((byte) -1, new BoundingBox(42, -218, 491, 676)); //Scommaaccent
		widths.put((byte) -1, 722); //Ohungarumlaut
		boxes.put((byte) -1, new BoundingBox(34, -14, 688, 890)); //Ohungarumlaut
		widths.put((byte) -1, 400); //degree
		boxes.put((byte) -1, new BoundingBox(57, 390, 343, 676)); //degree
		widths.put((byte) -1, 500); //ograve
		boxes.put((byte) -1, new BoundingBox(29, -10, 470, 678)); //ograve
		widths.put((byte) -1, 667); //Ccaron
		boxes.put((byte) -1, new BoundingBox(28, -14, 633, 886)); //Ccaron
		widths.put((byte) -1, 500); //ugrave
		boxes.put((byte) -1, new BoundingBox(9, -10, 479, 678)); //ugrave
		widths.put((byte) -1, 453); //radical
		boxes.put((byte) -1, new BoundingBox(2, -60, 452, 768)); //radical
		widths.put((byte) -1, 722); //Dcaron
		boxes.put((byte) -1, new BoundingBox(16, 0, 685, 886)); //Dcaron
		widths.put((byte) -1, 333); //rcommaaccent
		boxes.put((byte) -1, new BoundingBox(5, -218, 335, 460)); //rcommaaccent
		widths.put((byte) -1, 722); //Ntilde
		boxes.put((byte) -1, new BoundingBox(12, -11, 707, 850)); //Ntilde
		widths.put((byte) -1, 500); //otilde
		boxes.put((byte) -1, new BoundingBox(29, -10, 470, 638)); //otilde
		widths.put((byte) -1, 667); //Rcommaaccent
		boxes.put((byte) -1, new BoundingBox(17, -198, 659, 662)); //Rcommaaccent
		widths.put((byte) -1, 611); //Lcommaaccent
		boxes.put((byte) -1, new BoundingBox(12, -218, 598, 662)); //Lcommaaccent
		widths.put((byte) -1, 722); //Atilde
		boxes.put((byte) -1, new BoundingBox(15, 0, 706, 850)); //Atilde
		widths.put((byte) -1, 722); //Aogonek
		boxes.put((byte) -1, new BoundingBox(15, -165, 738, 674)); //Aogonek
		widths.put((byte) -1, 722); //Aring
		boxes.put((byte) -1, new BoundingBox(15, 0, 706, 898)); //Aring
		widths.put((byte) -1, 722); //Otilde
		boxes.put((byte) -1, new BoundingBox(34, -14, 688, 850)); //Otilde
		widths.put((byte) -1, 444); //zdotaccent
		boxes.put((byte) -1, new BoundingBox(27, 0, 418, 623)); //zdotaccent
		widths.put((byte) -1, 611); //Ecaron
		boxes.put((byte) -1, new BoundingBox(12, 0, 597, 886)); //Ecaron
		widths.put((byte) -1, 333); //Iogonek
		boxes.put((byte) -1, new BoundingBox(18, -165, 315, 662)); //Iogonek
		widths.put((byte) -1, 500); //kcommaaccent
		boxes.put((byte) -1, new BoundingBox(7, -218, 505, 683)); //kcommaaccent
		widths.put((byte) -1, 564); //minus
		boxes.put((byte) -1, new BoundingBox(30, 220, 534, 286)); //minus
		widths.put((byte) -1, 333); //Icircumflex
		boxes.put((byte) -1, new BoundingBox(11, 0, 322, 886)); //Icircumflex
		widths.put((byte) -1, 500); //ncaron
		boxes.put((byte) -1, new BoundingBox(16, 0, 485, 674)); //ncaron
		widths.put((byte) -1, 278); //tcommaaccent
		boxes.put((byte) -1, new BoundingBox(13, -218, 279, 579)); //tcommaaccent
		widths.put((byte) -1, 564); //logicalnot
		boxes.put((byte) -1, new BoundingBox(30, 108, 534, 386)); //logicalnot
		widths.put((byte) -1, 500); //odieresis
		boxes.put((byte) -1, new BoundingBox(29, -10, 470, 623)); //odieresis
		widths.put((byte) -1, 500); //udieresis
		boxes.put((byte) -1, new BoundingBox(9, -10, 479, 623)); //udieresis
		widths.put((byte) -1, 549); //notequal
		boxes.put((byte) -1, new BoundingBox(12, -31, 537, 547)); //notequal
		widths.put((byte) -1, 500); //gcommaaccent
		boxes.put((byte) -1, new BoundingBox(28, -218, 470, 749)); //gcommaaccent
		widths.put((byte) -1, 500); //eth
		boxes.put((byte) -1, new BoundingBox(29, -10, 471, 686)); //eth
		widths.put((byte) -1, 444); //zcaron
		boxes.put((byte) -1, new BoundingBox(27, 0, 418, 674)); //zcaron
		widths.put((byte) -1, 500); //ncommaaccent
		boxes.put((byte) -1, new BoundingBox(16, -218, 485, 460)); //ncommaaccent
		widths.put((byte) -1, 300); //onesuperior
		boxes.put((byte) -1, new BoundingBox(57, 270, 248, 676)); //onesuperior
		widths.put((byte) -1, 278); //imacron
		boxes.put((byte) -1, new BoundingBox(6, 0, 271, 601)); //imacron
		widths.put((byte) -1, 500); //Euro
		boxes.put((byte) -1, new BoundingBox(0, 0, 0, 0)); //Euro
	}
}
