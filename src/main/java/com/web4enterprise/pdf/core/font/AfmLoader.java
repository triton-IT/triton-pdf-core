package com.web4enterprise.pdf.core.font;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.web4enterprise.pdf.core.BoundingBox;

public class AfmLoader {
	private AfmLoader() {
		throw new IllegalAccessError("Utility class");
	}
	public static FontVariant load(InputStream stream) throws IOException {
		Map<String, Byte> nameByteMapping = new HashMap<>();
		
		FontVariant fontVariant = new FontVariant();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

		String line = reader.readLine();

		while (line != null) {
			if (line.startsWith("FontName ")) {
				String[] tokens = line.split(" ");
				fontVariant.setName(tokens[1]);
			} else if (line.startsWith("UnderlinePosition ")) {
				String[] tokens = line.split(" ");
				fontVariant.setUnderlinePosition(Integer.parseInt(tokens[1]));
			} else if (line.startsWith("UnderlineThickness ")) {
				String[] tokens = line.split(" ");
				fontVariant.setUnderlineThickness(Integer.parseInt(tokens[1]));
			} else if (line.startsWith("C ")) {
				String[] tokens = line.split(" ");
				byte c = (byte) Integer.parseInt(tokens[1]);
				//Do not parse bytes with unknown char code. Doesn't need them for now.
				if(c != -1) {
					//Add width
					fontVariant.addWidth(c, Integer.parseInt(tokens[4]));
					
					//Store name for further use.
					String name = tokens[7];
					nameByteMapping.put(name, c);
					
					//Add BoundingBox
					fontVariant.addBox(c, new BoundingBox(Integer.parseInt(tokens[10]),  
							Integer.parseInt(tokens[11]),  
							Integer.parseInt(tokens[12]),  
							Integer.parseInt(tokens[13])));
				}
			} else if (line.startsWith("KPX ")) {
				String[] tokens = line.split(" ");
				Byte source = nameByteMapping.get(tokens[1]);
				Byte destination = nameByteMapping.get(tokens[2]);
				if(source != null && destination != null) {
					fontVariant.addKerning(source, destination, Integer.parseInt(tokens[3]));
				}
			}

			line = reader.readLine();
		}
		
		return fontVariant;
	}

}
