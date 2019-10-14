package com.Finden.findenBackEnd;

import java.util.ArrayList;
import java.util.List;
import org.kabeja.dxf.*;
import org.kabeja.parser.*;
public class ReadAutocadFile {
	/** 
	 
	 **/
    public static ArrayList<String> getAutocadFile(String filePath) throws ParseException {
        ArrayList<String> puertosVD = new ArrayList<>();
        ArrayList<String> puertos = new ArrayList<>();
        Parser parser = ParserBuilder.createDefaultParser();
        parser.parse(filePath, DXFParser.DEFAULT_ENCODING);
        DXFDocument doc = parser.getDocument();
        List<DXFText> lst = doc.getDXFLayer("PUERTOS").getDXFEntities(DXFConstants.ENTITY_TYPE_TEXT);
        //lst palabras encontradas en la capa MUROS
        //Cantidad de puertos lst.size();
        System.out.println(lst.size());
        for (int index = 0; index < lst.size(); index++) {
        	puertos.add(lst.get(index).getText());
    		if(lst.get(index).getText().contains("VD")) {//puertos que tengan la secuencia VD
        		puertosVD.add(lst.get(index).getText());
        	}
        }
        return puertos;
    }
}