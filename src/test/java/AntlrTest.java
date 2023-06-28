import com.alibaba.fastjson.parser.JSONLexer;
import jdk.nashorn.internal.parser.JSONParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

public class AntlrTest {
    
    @Test
    public void testJson2Sql() {
        String jsonStr = "{\n" + "    \"value\":\"高度活跃\",\n" + "    \"type\":\"rules_relation\",\n"
            + "    \"relation\":\"and\",\n" + "    \"rules\":[\n" + "        {\n"
            + "            \"type\":\"rules_relation\",\n" + "            \"relation\":\"and\",\n"
            + "            \"rules\":[\n" + "                {\n" + "                    \"measure\":{\n"
            + "                        \"aggregator\":\"general\",\n" + "                        \"field\":\"\",\n"
            + "                        \"type\":\"event_measure\",\n"
            + "                        \"event_name\":\"$AppStart\"\n" + "                    },\n"
            + "                    \"type\":\"event_rule\",\n"
            + "                    \"time_function\":\"relative_time\",\n" + "                    \"time_params\":[\n"
            + "                        \"30\"\n" + "                    ],\n" + "                    \"params\":[\n"
            + "                        10\n" + "                    ],\n"
            + "                    \"function\":\"least\",\n" + "                    \"filters\":[\n" + "\n"
            + "                    ]\n" + "                }\n" + "            ]\n" + "        }\n" + "    ],\n"
            + "    \"comment\":\"\"\n" + "}" ;
//        CodePointCharStream input = CharStreams.fromString(jsonStr);
//        JSONLexer jsonLexer = new JSONLexer(input);
//
//        JSONLexer lexer = new JSONLexer(CharStreams.fromString(jsonStr));
//        CommonTokenStream tokens = new CommonTokenStream(lexer);  //生成token
//        JSONParser parser = new JSONParser(tokens);
//        JSONParser.ObjContext objCtx = parser.obj();
    }
}
