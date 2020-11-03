package com.tlw.tool.unicode;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2008-12-29
@version:2008-12-29
Descript:展示unicode区段
 */
public class UnicodeShower {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame=new JFrame();
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final JList jlistUnicodeBlock=new JList(blocks);
		JScrollPane jscrollList=new JScrollPane(jlistUnicodeBlock);
		//final JPanel jpanelData=new JPanel();
		//final JScrollPane jscrollData=new JScrollPane(jpanelData);
		//final Font font=new Font("宋体",Font.PLAIN,12);
		final JTable jtableData=new JTable();
		JScrollPane jscrollTable=new JScrollPane(jtableData);
		
		jlistUnicodeBlock.addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int idx=jlistUnicodeBlock.getSelectedIndex();
				int start=blockStarts[idx];
				int end=((idx+1)<blockStarts.length)?blockStarts[idx+1]:0x10000;
				/*
				int colCount=10;
				jpanelData.removeAll();
				jpanelData.setLayout(new GridLayout(0,colCount));
				for(int i=start;i<end;i++){
					JLabel lb=new JLabel((char)i+"");
					lb.setBorder(BorderFactory.createBevelBorder(1));
					lb.setToolTipText("Unicode:"+i);
					lb.setFont(font);
					jpanelData.add(lb);
				}
				jpanelData.validate();
				*/
				UnicodeTableModel tableModel=new UnicodeTableModel(start,end);
				jtableData.setModel(tableModel);
			}
		});

		frame.add(jscrollList,"West");
		//frame.add(jscrollData,"Center");
		frame.add(jscrollTable,"Center");
		frame.setVisible(true);
	}
    private static final char blockStarts[] = {
        '\u0000', // Basic Latin
        '\u0080', // Latin-1 Supplement
        '\u0100', // Latin Extended-A
        '\u0180', // Latin Extended-B
        '\u0250', // IPA Extensions
        '\u02B0', // Spacing Modifier Letters
        '\u0300', // Combining Diacritical Marks
        '\u0370', // Greek
        '\u0400', // Cyrillic
        '\u0500', // unassigned
        '\u0530', // Armenian
        '\u0590', // Hebrew
        '\u0600', // Arabic
        '\u0700', // Syriac
        '\u0750', // unassigned
        '\u0780', // Thaana
        '\u07C0', // unassigned
        '\u0900', // Devanagari
        '\u0980', // Bengali
        '\u0A00', // Gurmukhi
        '\u0A80', // Gujarati
        '\u0B00', // Oriya
        '\u0B80', // Tamil
        '\u0C00', // Telugu
        '\u0C80', // Kannada
        '\u0D00', // Malayalam
        '\u0D80', // Sinhala
        '\u0E00', // Thai
        '\u0E80', // Lao
        '\u0F00', // Tibetan
        '\u1000', // Myanmar
        '\u10A0', // Georgian
        '\u1100', // Hangul Jamo
        '\u1200', // Ethiopic
        '\u1380', // unassigned
        '\u13A0', // Cherokee
        '\u1400', // Unified Canadian Aboriginal Syllabics
        '\u1680', // Ogham
        '\u16A0', // Runic
        '\u1700', // unassigned
        '\u1780', // Khmer
        '\u1800', // Mongolian
        '\u18B0', // unassigned
        '\u1E00', // Latin Extended Additional
        '\u1F00', // Greek Extended
        '\u2000', // General Punctuation
        '\u2070', // Superscripts and Subscripts
        '\u20A0', // Currency Symbols
        '\u20D0', // Combining Marks for Symbols
        '\u2100', // Letterlike Symbols
        '\u2150', // Number Forms
        '\u2190', // Arrows
        '\u2200', // Mathematical Operators
        '\u2300', // Miscellaneous Technical
        '\u2400', // Control Pictures
        '\u2440', // Optical Character Recognition
        '\u2460', // Enclosed Alphanumerics
        '\u2500', // Box Drawing
        '\u2580', // Block Elements
        '\u25A0', // Geometric Shapes
        '\u2600', // Miscellaneous Symbols
        '\u2700', // Dingbats
        '\u27C0', // unassigned
        '\u2800', // Braille Patterns
        '\u2900', // unassigned
        '\u2E80', // CJK Radicals Supplement
        '\u2F00', // Kangxi Radicals
        '\u2FE0', // unassigned
        '\u2FF0', // Ideographic Description Characters
        '\u3000', // CJK Symbols and Punctuation
        '\u3040', // Hiragana
        '\u30A0', // Katakana
        '\u3100', // Bopomofo
        '\u3130', // Hangul Compatibility Jamo
        '\u3190', // Kanbun
        '\u31A0', // Bopomofo Extended
        '\u31C0', // unassigned
        '\u3200', // Enclosed CJK Letters and Months
        '\u3300', // CJK Compatibility
        '\u3400', // CJK Unified Ideographs Extension A
        '\u4DB6', // unassigned
        '\u4E00', // CJK Unified Ideographs
        '\uA000', // Yi Syllables
        '\uA490', // Yi Radicals
        '\uA4D0', // unassigned
        '\uAC00', // Hangul Syllables
        '\uD7A4', // unassigned
        '\uD800', // Surrogates
        '\uE000', // Private Use
        '\uF900', // CJK Compatibility Ideographs
        '\uFB00', // Alphabetic Presentation Forms
        '\uFB50', // Arabic Presentation Forms-A
        '\uFE00', // unassigned
        '\uFE20', // Combining Half Marks
        '\uFE30', // CJK Compatibility Forms
        '\uFE50', // Small Form Variants
        '\uFE70', // Arabic Presentation Forms-B
        '\uFEFF', // Specials
        '\uFF00', // Halfwidth and Fullwidth Forms
        '\uFFF0', // Specials
        '\uFFFE', // non-characters
    };
    private static final String[] blocks = {
        "BASIC_LATIN",
        "LATIN_1_SUPPLEMENT",
        "LATIN_EXTENDED_A",
        "LATIN_EXTENDED_B",
        "IPA_EXTENSIONS",
        "SPACING_MODIFIER_LETTERS",
        "COMBINING_DIACRITICAL_MARKS",
        "GREEK",
        "CYRILLIC",
        "null",
        "ARMENIAN",
        "HEBREW",
        "ARABIC",
        "SYRIAC",
        "null",
        "THAANA",
        "null",
        "DEVANAGARI",
        "BENGALI",
        "GURMUKHI",
        "GUJARATI",
        "ORIYA",
        "TAMIL",
        "TELUGU",
        "KANNADA",
        "MALAYALAM",
        "SINHALA",
        "THAI",
        "LAO",
        "TIBETAN",
        "MYANMAR",
        "GEORGIAN",
        "HANGUL_JAMO",
        "ETHIOPIC",
        "null",
        "CHEROKEE",
        "UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS",
        "OGHAM",
        "RUNIC",
        "null",
        "KHMER",
        "MONGOLIAN",
        "null",
        "LATIN_EXTENDED_ADDITIONAL",
        "GREEK_EXTENDED",
        "GENERAL_PUNCTUATION",
        "SUPERSCRIPTS_AND_SUBSCRIPTS",
        "CURRENCY_SYMBOLS",
        "COMBINING_MARKS_FOR_SYMBOLS",
        "LETTERLIKE_SYMBOLS",
        "NUMBER_FORMS",
        "ARROWS",
        "MATHEMATICAL_OPERATORS",
        "MISCELLANEOUS_TECHNICAL",
        "CONTROL_PICTURES",
        "OPTICAL_CHARACTER_RECOGNITION",
        "ENCLOSED_ALPHANUMERICS",
        "BOX_DRAWING",
        "BLOCK_ELEMENTS",
        "GEOMETRIC_SHAPES",
        "MISCELLANEOUS_SYMBOLS",
        "DINGBATS",
        "null",
        "BRAILLE_PATTERNS",
        "null",
        "CJK_RADICALS_SUPPLEMENT",
        "KANGXI_RADICALS",
        "null",
        "IDEOGRAPHIC_DESCRIPTION_CHARACTERS",
        "CJK_SYMBOLS_AND_PUNCTUATION",
        "HIRAGANA",
        "KATAKANA",
        "BOPOMOFO",
        "HANGUL_COMPATIBILITY_JAMO",
        "KANBUN",
        "BOPOMOFO_EXTENDED",
        "null",
        "ENCLOSED_CJK_LETTERS_AND_MONTHS",
        "CJK_COMPATIBILITY",
        "CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A",
        "null",
        "CJK_UNIFIED_IDEOGRAPHS",
        "YI_SYLLABLES",
        "YI_RADICALS",
        "null",
        "HANGUL_SYLLABLES",
        "null",
        "SURROGATES_AREA",
        "PRIVATE_USE_AREA",
        "CJK_COMPATIBILITY_IDEOGRAPHS",
        "ALPHABETIC_PRESENTATION_FORMS",
        "ARABIC_PRESENTATION_FORMS_A",
        "null",
        "COMBINING_HALF_MARKS",
        "CJK_COMPATIBILITY_FORMS",
        "SMALL_FORM_VARIANTS",
        "ARABIC_PRESENTATION_FORMS_B",
        "SPECIALS",
        "HALFWIDTH_AND_FULLWIDTH_FORMS",
        "SPECIALS",
        "null",
    };
    static class UnicodeTableModel extends AbstractTableModel{
		private static final long serialVersionUID = -1309802110611959193L;
		int startChar,endChar,startAdd,endAdd,rowCount;
    	static final int COLUMN_COUNT=16;
    	public UnicodeTableModel(int start,int end){
    		startChar=start;
    		endChar=end;
			startAdd=startChar%COLUMN_COUNT;
			endAdd=endChar%COLUMN_COUNT;
			rowCount=((endChar-endAdd)-(startChar+startAdd))/COLUMN_COUNT;
    	}
		@Override
		public int getColumnCount() {
			return COLUMN_COUNT+1;
		}

		@Override
		public int getRowCount() {
			return rowCount+(startAdd==0?0:1)+(endAdd==0?0:1);
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			if(columnIndex==0){
				return "0x"+Integer.toHexString(rowIndex*COLUMN_COUNT+startChar-startAdd).toUpperCase();
			}else{
				if(rowIndex==0){
					if(columnIndex<startAdd)return ' ';
				}else if(rowIndex==rowCount){
					if(columnIndex>(COLUMN_COUNT-endAdd))return ' ';
				}
				return (char)(rowIndex*COLUMN_COUNT+columnIndex+startChar-startAdd-1);
			}
		}
		@Override
		public String getColumnName(int column) {
			if(column==0){
				return "Unicode:";
			}else{
				return Integer.toHexString(column-1).toUpperCase();
			}
		}
		
		/*@Override
		public Class<?> getColumnClass(int columnIndex) {
			if(columnIndex==0){
				return String.class;
			}else{
				return char.class;
			}
		}
		*/
    }
}
