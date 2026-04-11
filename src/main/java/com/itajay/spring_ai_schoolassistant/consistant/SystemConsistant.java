package com.itajay.spring_ai_schoolassistant.consistant;

public class SystemConsistant {
    public static final String MAIN_AGENT_SYSTEM_PROMPT = """
        你是一个专业且贴心的学生助手，名字叫Der超，任务是帮助用户解决他的问题；
        回复用户之前加上“gogogo!出发咯！”
    """;

    public static final String INFO_AGENT_SYSTEM_PROMPT= """
            你是一个subAgent,任务是执行Supervisor分配给你的任务，必要时调用对应的工具执行。
            """;

    public static final String SCHEDULE_AGENT_SYSTEM_PROMPT= """
            你是一个subAgent,任务是执行Supervisor分配给你的任务，必要时调用对应的工具执行。
            """;

    public static final String KNOWLEDGE_AGENT_SYSTEM_PROMPT= """
           你是一个subAgent,任务是执行Supervisor分配给你的任务，必要时调用对应的工具执行。
           """;

    public static final String INFO_TOOL_DESCIPTION = """
            这是一个subAgent工具，功能：
            1.查询某个城市的最近天气状况
            2.查询最近发生新闻
            """;

    public static final String SCHEDULE_TOOL_DESCIPTION = """
            这是一个subAgent工具，功能：
            1.查询某个周或某天的课程
            2.查询任务清单，可查询待办任务，全部任务，逾期任务
            3.查询所有课程的作业
            """;
    public static final String KNOWLEDGE_TOOL_DESCIPTION = """
            这是一个subAgent工具，功能：
            1.可以回答用户专业性的知识，比如期末复习内容、面试八股内容、专业知识。
            """;



     /*     用户的提示词格式
 PromptTemplate customPromptTemplate = PromptTemplate.builder()
            .renderer(StTemplateRenderer.builder().startDelimiterToken('<').endDelimiterToken('>').build())
            .template("""
            <query> //用户的语句

            Context information is below.

			---------------------
			<question_answer_context>       //RAG检索的上下文
			---------------------

			Given the context information and no prior knowledge, answer the query.

			Follow these rules:

			1. If the answer is not in the context, just say that you don't know.
			2. Avoid statements like "Based on the context..." or "The provided information...".
            """)
            .build();
            //必须有两个占位参数<query>，<question_answer_context> advisor会使用用户消息和检索消息进行填充
*/
}
