package com.tlw.tool.bnf.antlr;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2008-12-1
@version:2008-12-1
Descript:把SQL92规范附带的bnf文件转换到类似于antlr能够读取的.g文件
 */
public class Bnf2Antlr {
	static final String bnfFile="D:\\book\\DB1\\sql-92_bnf.txt";
	static final String gFile="D:\\book\\DB1\\sql-92.bnf.g";
	public static void main(String[] args) throws Exception {
		String bnfContent=getBNFContent();
		//原文第517行缺少>
		bnfContent=bnfContent.replaceAll("FLOAT [ <left paren> <precision <right paren> ]","FLOAT [ <left paren> <precision> <right paren> ]");
		//原文前3行没有注释掉 
		bnfContent=bnfContent.replaceAll("BNF Grammar for ISO/IEC 9075:1992 - Database Language SQL (SQL-92)","--li BNF Grammar for ISO/IEC 9075:1992 - Database Language SQL (SQL-92)");
		bnfContent=bnfContent.replaceAll("==================================================================","--li ==================================================================");
		bnfContent=bnfContent.replaceAll("@(#)$Id: sql-92.bnf,v 2.3 2004/03/31 19:34:09 jleffler Exp $","--li @(#)$Id: sql-92.bnf,v 2.3 2004/03/31 19:34:09 jleffler Exp $");
		
		//进行一些常规的符号转换
		bnfContent=bnfContent.replaceAll("::=", ":");
		bnfContent=bnfContent.replaceAll("--hr", "//");
		bnfContent=bnfContent.replaceAll("--li", "//");	
		
		bnfContent=bnfContent.replaceAll("--p", "/*");
		bnfContent=bnfContent.replaceAll("--/p", "*/");
		
		bnfContent=bnfContent.replaceAll("--h2", "/*");
		bnfContent=bnfContent.replaceAll("--/h2", "*/");

		bnfContent=bnfContent.replaceAll("--bl", "/*");
		bnfContent=bnfContent.replaceAll("--/bl", "*/");
		bnfContent=bnfContent.replaceAll("--small", "/*");
		bnfContent=bnfContent.replaceAll("--/small", "*/");
		bnfContent=bnfContent.replaceAll("--i", "/*");
		bnfContent=bnfContent.replaceAll("--/i", "*/");
		
		Pattern pattern=null;
		Matcher matcher=null;
		
		//寻找尖括号中的内容，并替换其中的' '和':'为'_'
		pattern=Pattern.compile("<[^>^\\n]*>");
		matcher=pattern.matcher(bnfContent);
		while(matcher.find()){
			String found=matcher.group();
			found=found.replace(' ', '_');
			found=found.replaceAll(":", "_");
			found=found.replaceAll("-", "___");
			found=found.substring(1,found.length()-1);
			bnfContent=bnfContent.replaceAll(matcher.group(), found);
			//System.out.println(matcher.group()+" "+found);
		}
		
		//为短语定义(超过1行的)添加作为结尾的分号
		pattern=Pattern.compile(":[\\w\\n\\s\\|\\!\\{\\[\\}\\]\\.\\-\\\\\\*]*");
		matcher=pattern.matcher(bnfContent);
		Vector founds=new Vector();
		while(matcher.find()){
			String found=matcher.group();
			//System.out.println(found);
			//System.out.println("-------------|"+found.lastIndexOf('\n'));
			founds.add(found);
		}
		for(int i=0;i<founds.size();i++){
			String bePlace=(String)founds.get(i);
			int lastNewlineIdx=bePlace.lastIndexOf('\n');
			int newlineCount=bePlace.split("\n").length;//注意：包含的回车数量应大于1，否则可能出现:回车的情况
			if(lastNewlineIdx>-1 && newlineCount>2){
				String place=bePlace.subSequence(0, lastNewlineIdx)+";"+bePlace.substring(lastNewlineIdx);
				//System.out.println(place);
				//System.out.println("-------------|");
				bnfContent=bnfContent.replaceAll(bePlace, place);
			}
		}
		//为最后一行加分号,由于正则是分析了分号之间的内容，最后一行之后没有分号所以解析不到
		bnfContent=bnfContent.replaceAll("high : 2 | High left_paren 2 right_paren", "high : 2 | High left_paren 2 right_paren;");
		
		
		
		//替换一些常量
		bnfContent=bnfContent.replaceAll("A | B | C | D | E | F | G | H | I | J | K | L | M | N | O | P | Q | R | S | T | U | V | W | X | Y | Z", "'A'..'Z'");
		bnfContent=bnfContent.replaceAll("a | b | c | d | e | f | g | h | i | j | k | l | m | n | o | p | q | r | s | t | u | v | w | x | y | z","'a'..'z'");
		bnfContent=bnfContent.replaceAll("0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9","'0'..'9'");
		
		//为所有词法定义或者在上边未生效的单行句法定义，添加末尾的分号
		bnfContent=bnfContent.replaceAll("\ndouble_quote : \"","\ndouble_quote : '\"';");
		bnfContent=bnfContent.replaceAll("\npercent : %","\npercent : '%';");
		bnfContent=bnfContent.replaceAll("\nampersand : &","\nampersand : '&';");
		bnfContent=bnfContent.replaceAll("\nquote : '","\nquote : '''';");
		bnfContent=bnfContent.replaceAll("\nleft_paren : (","\nleft_paren : '(';");
		bnfContent=bnfContent.replaceAll("\nright_paren : )","\nright_paren : ')';");
		bnfContent=bnfContent.replaceAll("\nasterisk : *","\nasterisk : '*';");
		bnfContent=bnfContent.replaceAll("\nplus_sign : +","\nplus_sign : '+';");
		bnfContent=bnfContent.replaceAll("\ncomma : ,","\ncomma : ',';");
		bnfContent=bnfContent.replaceAll("\nminus_sign : -","\nminus_sign : '-';");
		bnfContent=bnfContent.replaceAll("\nperiod : .","\nperiod : '.';");
		bnfContent=bnfContent.replaceAll("\nsolidus : /","\nsolidus : '/';");
		bnfContent=bnfContent.replaceAll("\ncolon : :","\ncolon : ':'");
		bnfContent=bnfContent.replaceAll("\nsemicolon : ;","\nsemicolon : ';';");
		bnfContent=bnfContent.replaceAll("\nless_than_operator : <","\nless_than_operator : '<';");
		bnfContent=bnfContent.replaceAll("\nequals_operator : =","\nequals_operator : '=';");
		bnfContent=bnfContent.replaceAll("\ngreater_than_operator : >","\ngreater_than_operator : '>';");
		bnfContent=bnfContent.replaceAll("\nquestion_mark : ?","\nquestion_mark : '?';");
		bnfContent=bnfContent.replaceAll("\nunderscore : _","\nunderscore : '_'");
		bnfContent=bnfContent.replaceAll("\nvertical_bar : |","\nvertical_bar : '|'");
		bnfContent=bnfContent.replaceAll("\nleft_bracket : [","\nleft_bracket : '['");
		bnfContent=bnfContent.replaceAll("\nright_bracket : ]","\nright_bracket : ']'");
		bnfContent=bnfContent.replaceAll("not_equals_operator : ;","not_equals_operator : ';';");
		bnfContent=bnfContent.replaceAll("greater_than_or_equals_operator : >=","greater_than_or_equals_operator : '>=';");
		bnfContent=bnfContent.replaceAll("less_than_or_equals_operator : <=","less_than_or_equals_operator : '<=';");
		bnfContent=bnfContent.replaceAll("concatenation_operator : ||","concatenation_operator : '||;'");//这句不生效很奇怪。在尾部重新执行
		bnfContent=bnfContent.replaceAll("double_period : ..","double_period : '..';");
		
		//去掉一些不必须要的空行
		bnfContent=bnfContent.replaceAll("\n;",";");
		bnfContent=bnfContent.replaceAll("\n\n","\n");

		//[ ]转换为( )?
		bnfContent=bnfContent.replaceAll("[ ","( ");
		bnfContent=bnfContent.replaceAll(" ]"," )?");
		//{}...转换为()
		bnfContent=bnfContent.replaceAll("{ ","( ");
		bnfContent=bnfContent.replaceAll("}...",")*");
		bnfContent=bnfContent.replaceAll("} ...",")*");
		bnfContent=bnfContent.replaceAll("} ","} ");
		//所有独立的...转换为*
		bnfContent=bnfContent.replaceAll("...", "*");
		
		//转换一些单行的从句定义，不会自动被加末尾分号
		bnfContent=bnfContent.replaceAll("statement_name : identifier","statement_name : identifier;");
		bnfContent=bnfContent.replaceAll("referencing_columns : reference_column_list","referencing_columns : reference_column_list;");
		bnfContent=bnfContent.replaceAll("current_timestamp_value_function : CURRENT_TIMESTAMP ( left_paren timestamp_precision right_paren )?","current_timestamp_value_function : CURRENT_TIMESTAMP ( left_paren timestamp_precision right_paren )?;");
		bnfContent=bnfContent.replaceAll("truth_value : TRUE | FALSE | UNKNOWN","truth_value : TRUE | FALSE | UNKNOWN;");
		bnfContent=bnfContent.replaceAll("set_quantifier : DISTINCT | ALL","set_quantifier : DISTINCT | ALL;");
		bnfContent=bnfContent.replaceAll("check_constraint_definition : CHECK left_paren search_condition right_paren","check_constraint_definition : CHECK left_paren search_condition right_paren;");
		bnfContent=bnfContent.replaceAll("drop_assertion_statement : DROP ASSERTION constraint_name","drop_assertion_statement : DROP ASSERTION constraint_name;");
		bnfContent=bnfContent.replaceAll("query_primary : non___join_query_primary | joined_table","query_primary : non___join_query_primary | joined_table;");
		bnfContent=bnfContent.replaceAll("grantee := PUBLIC | authorization_identifier","grantee := PUBLIC | authorization_identifier;");
		bnfContent=bnfContent.replaceAll("rollback_statement : ROLLBACK ( WORK )?","rollback_statement : ROLLBACK ( WORK )?;");
		bnfContent=bnfContent.replaceAll("action_list : action ( ( comma action> )* )?","action_list : action ( ( comma action> )* )?;");
		bnfContent=bnfContent.replaceAll("disconnect_object : connection_object | ALL | CURRENT","disconnect_object : connection_object | ALL | CURRENT;");
		bnfContent=bnfContent.replaceAll("direct_implementation___defined_statement : !! See the syntax rules","direct_implementation___defined_statement : '!! See the syntax rules';");
		bnfContent=bnfContent.replaceAll("set_time_zone_value : interval_value_expression | LOCAL","set_time_zone_value : interval_value_expression | LOCAL;");
		bnfContent=bnfContent.replaceAll("column_name : identifier","column_name : identifier;");
		bnfContent=bnfContent.replaceAll("current_timestamp_value_function : CURRENT_TIMESTAMP ( left_paren timestamp_precision right_paren )?","current_timestamp_value_function : CURRENT_TIMESTAMP ( left_paren timestamp_precision right_paren )?;");
		bnfContent=bnfContent.replaceAll("comment_introducer : minus_signminus_sign [minus_sign*];","comment_introducer : minus_signminus_sign minus_sign*;");
		bnfContent=bnfContent.replaceAll("\nSQL_variant : 1987 | 1989 | 1992;","");
		bnfContent=bnfContent.replaceAll("","");
		
		
		//TODO:处理一些有待详细定义的段
		bnfContent=bnfContent.replaceAll("!! See the Syntax rules","'!! See the Syntax rules'");
		bnfContent=bnfContent.replaceAll("!! space character in character set in use","' '");
		bnfContent=bnfContent.replaceAll("!! implementation defined end of line indicator","'!! implementation defined end of line indicator'");

		//分析并生成尚未被定义的lexer
		bnfContent=parseCaseInsensitivityLexer(bnfContent);
		
		//替换大小写
		bnfContent=bnfContent.replaceAll(" quote "," QUOTE ");
		bnfContent=bnfContent.replaceAll(" left_pentar "," LEFT_PENTAR ");
		bnfContent=bnfContent.replaceAll(" quote "," QUOTE ");
		bnfContent=bnfContent.replaceAll(" quote "," QUOTE ");
		bnfContent=bnfContent.replaceAll(" quote "," QUOTE ");
		bnfContent=bnfContent.replaceAll(" quote "," QUOTE ");
		bnfContent=bnfContent.replaceAll(" quote "," QUOTE ");
		bnfContent=bnfContent.replaceAll(" quote "," QUOTE ");
		bnfContent=bnfContent.replaceAll(" quote "," QUOTE ");
		bnfContent=bnfContent.replaceAll(" quote "," QUOTE ");
		bnfContent=bnfContent.replaceAll(" quote "," QUOTE ");
		//bnfContent=bnfContent.replaceAll("","");
		saveFile(bnfContent,gFile);
		System.out.println("Done...");
	}
	public static String getBNFContent() throws Exception{
		return getFileContent(bnfFile);
	}
	public static String getGFileContent() throws Exception{
		return getFileContent(gFile);
	}
	public static void saveFile(String bnfContent,String file) throws Exception{
		FileWriter fw=new FileWriter(gFile);
		fw.write(bnfContent);
		fw.flush();
		fw.close();
	}
	private static String getFileContent(String fileName) throws Exception{
		FileReader fr=new FileReader(fileName);
		BufferedReader br=new BufferedReader(fr);
		String content="",line="";
		while((line=br.readLine())!=null){
			content+=line+"\n";
		}
		br.close();
		fr.close();
		return content;
	}
	public static String parseCaseInsensitivityLexer(String bnfContent){
		//解析所有的定义
		Pattern pattern=Pattern.compile("(\\A|\\n)[\\w\\s]*:");
		Matcher matcher=pattern.matcher(bnfContent);
		Set rules=new HashSet();
		while(matcher.find()){
			String rule=matcher.group().replace(':', ' ').trim();
			rules.add(rule);
			//System.out.println(rule);
		}
		
		//解析每一个定义的内容<String,String>
		HashMap lexers=new HashMap();
		Iterator rulesIterator=rules.iterator();
		while(rulesIterator.hasNext()){
			String rule=(String)rulesIterator.next();
			String regex="(\\n|\\A)"+rule+"[\\s]*:[^;]*;";
			pattern=Pattern.compile(regex);
			//System.out.println(regex);
			matcher=pattern.matcher(bnfContent);
			matcher.find();
			String content=matcher.group();
			//System.out.println(rule);
			//System.out.println(content);
			//System.out.println("----------------");
			//*
			//解析用到的其他定义或者Lexer
			pattern=Pattern.compile("[\\w]+");
			matcher=pattern.matcher(content);
			matcher.find();//忽略第一次解析为定义本身
			while(matcher.find()){
				String lexer=matcher.group().replace('\n', ' ').trim();
				if(!rules.contains(lexer) && lexers.get(lexer)==null){
					//如果此lexer尚未被定义，那么记录它
					String desc="";
					for(int i=0;i<lexer.length();i++){
						char ch=lexer.charAt(i);
						char chUp=Character.toUpperCase(ch);
						char chLo=Character.toLowerCase(ch);
						desc+="( '"+chUp+"' | '"+chLo+"' ) ";
					}
					desc+=";";
					lexers.put(lexer, desc);
					//System.out.println(lexer+" : "+desc);
				}
			}//*/
		}
		
		//将尚未定义的Lexer写入bnfContent
		Iterator lexerIterator=lexers.keySet().iterator();
		while(lexerIterator.hasNext()){
			String lexer=(String)lexerIterator.next();
			bnfContent+="\n"+lexer+" : "+lexers.get(lexer);
		}
		return bnfContent;
	}
}
