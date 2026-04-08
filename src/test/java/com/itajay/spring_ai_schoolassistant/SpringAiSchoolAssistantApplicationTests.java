package com.itajay.spring_ai_schoolassistant;


import com.itajay.spring_ai_schoolassistant.entity.vo.CourseScheduleView;
import com.itajay.spring_ai_schoolassistant.service.CourseService;
import com.itajay.spring_ai_schoolassistant.service.impl.CourseServiceImpl;
import com.itajay.spring_ai_schoolassistant.service.impl.DocumentService;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@SpringBootTest
class SpringAiSchoolAssistantApplicationTests {
    @Autowired
     private DocumentService documentService;
    @Autowired
    private VectorStore vectorStore;
    @Autowired
    private CourseServiceImpl courseService;

    @Test
    public void testConvertor(){

    }



    @Test
    void contextLoads() {
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);
    }

    @Test
    public void testPagePdfReader(){
        FileSystemResource fileSystemResource = new FileSystemResource("src/main/resources/LeetCode经典面试题150解题思路总结.pdf");
        PagePdfDocumentReader pagePdfDocumentReader = new PagePdfDocumentReader(fileSystemResource);
        List<Document> documents = pagePdfDocumentReader.read();
        for(var doc:documents){
            System.out.println(doc.getMetadata());
            System.out.println(doc.getText());
        }
    }

    @Test
    public void testTikaReader(){
        FileSystemResource fileSystemResource = new FileSystemResource("src/main/resources/LeetCode经典面试题150解题思路总结.docx");
        ExtractedTextFormatter fromatter = ExtractedTextFormatter.builder().build();
        List<Document> original = new TikaDocumentReader(fileSystemResource).get();
        //文档划分器
        TokenTextSplitter splitter = TokenTextSplitter.builder()
                .withChunkSize(1000)       //token计数
                .withMinChunkSizeChars(400)
                .withKeepSeparator(true)
                .withPunctuationMarks(List.of('。', '？', '！'))
                .build();
        List<Document> splitted_documents = splitter.apply(original);
        for(var doc:splitted_documents){
            System.out.println(doc.getMetadata());
            System.out.println(doc.getText());
        }
    }




    @Test
    public void testDocumentService(){
        FileSystemResource fileSystemResource = new FileSystemResource("src/main/resources/LeetCode经典面试题150解题思路总结.pdf");
        documentService.EmbeddingDocument(fileSystemResource);
    }

    @Test
    public void testSerchDocument(){
        SearchRequest request = SearchRequest.builder()
                .query("接雨水")
                .filterExpression("source =='LeetCode经典面试题150解题思路总结.pdf'")
                .topK(2).build();
        List<Document> documents = vectorStore.similaritySearch(request);
        if(documents.isEmpty()){
            System.out.println("未查找到文档信息！");
        }else{
            for(var doc: documents){
                System.out.println(doc.getMetadata());
                System.out.println(doc.getText());
                System.out.println(doc.getScore());
            }
        }

    }

    @Test
    void testSplitter(){
        Document doc1 = new Document("""
                1.合并两个有序数组
                方法一：双指针（正向排序），i指针和j指针分别指向两个数组的0下标，每次比较两个指针指向的元素，指向较小的元素的指针往后移，直到某个数组的下标超出数组len。
                
                
                方法二：双指针（逆向排序）,由于正向排序需要辅助数组，可以采用从后往前比较。
                
                2.移除指定的元素（val)
                方法：采用双指针；i指针指向需保留元素的插入位置，j指针作为遍历指针遍历数组，如果遍历的元素不等于val，就把当前元素插入到i指针记录的位置，i指针后移。
                
                3.删除有序数组的重复项|
                方法：双指针；i指针指向已经插入的最后一个元素的位置（初始为0，表示已插入1个元素），j指针作为遍历指针（初始为1），如果遍历的元素不等于i指针指向的元素，就把当前元素插入到i指针的后一个位置。
                
                4.删除有序数组的重复项||
                方法：双指针；判断方法区别：如果当前遍历元素不等于已插入元素的最后一个元素，直接插入；反之继续判断当前元素是否不等于插入的倒数第二个元素（或已插入元素只有一个），不等于则插入元素，否则不插入。
                
                5.多数元素（找到数量大于一半的元素）
                方法：计数法；如果把众数表示+1，其他元素表示-1，那么数组总和一定大于0。把众数初始化为第一个元素，count设为1，如果后续遍历的元素等于众数，count++，反之count--；如果count==0，则设当前遍历元素作为众数，count=1，遍历完数组即可找到众数。
                
                6.轮转数组（将数组元素向右轮转k次）
                方法一：环状替换;
                证明：设坐标为x，数组长度为n，轮转后的位置为（x+k）%n，下一次轮转后坐标为（x+2k）%n，...，设经过b次轮转后回到初始位置，则有（x+bk)%n=x，则有bk=an（a表示轮转的环圈数），为使轮转圈数尽可能小，an应该尽可能小，即为n,k的最小公倍数，记为LCM。每次轮转环的元素个数为LCM/k，数组元素个数为n，故共需要轮转的次数为：n /(LCM/K)=nk/LCM，即为n，k的最大公约数，记为GCD。
                实现：经过GCD次循环（坐标x=0,...,GCD-1），每次循环中：将坐标为x的元素替换到next=（x+k）%n的位置，再轮转next的坐标，直到next的坐标等于x时，进入下一次循环。
                
                方法二：三次翻转数组
                实现：设数组的元素为（a1,a2,.....,an)；
                第一次翻转：（ak-1,ak-2,...,a1,ak,ak+1,...an)
                第二次翻转：（ak-1,ak-2,....,a1,an,an-1,...,ak)
                第三次翻转：（ak,ak+1,...an,a1,a2,..ak-1)
                
                7.买股票的最佳时机（只能购买一支股票）
                方法：记录前i-1天的最低购买股票价格MinPrice，则第i天的售卖股票的最大利润记为prices[i]-MinPrice，所以在遍历数组时不断的更新最低股票价格，即可算出当天售出股票的最大利润，同时记录全局最大利润即可。
                
                8.买股票的最佳时机（任意时刻只能持有一只股票）
                方法一：贪心算法。只要某一支股票后续能够给我们提供利润即买入，考虑第i天的股票价格，如果下一天的股票价格更高，则第i天的股票可以给我们提供利润，但如果下一天的价格更低，则无需购买第i天的股票，因为如果后续出现更高价格时的股票，第i+1天的股票可以提供的利润更多。
                
                方法二：动态规划。定义状态dp[i][0]和dp[i][1];dp[i][0]表示第i天不持有股票时的最大利润，dp[i][1]表示第i天持有股票时的最大利润。考虑dp[i][0]，若第i-1天不持有股票，则dp[i][0]=dp[i-1][0],若第i-1天持有股票，dp[i][0]=dp[i][i-1]+prices[i];考虑dp[i][1],若第i-1天持有股票，则dp[i][1]=dp[i-1][1],若第i-1天不持有股票，dp[i][1]=dp[i][0]-prices[i]；初始状态dp[0][0]=0,dp[0][1]=-prices[0]。
                则状态转移方程：
                优化：由于当前状态只与前一步状态有关，可以用Pre0和Pre1记录前一天两种情况下的最大利润。
                
                9.跳跃游戏（是否能到达最后一个下标）
                方法：贪心思想。实时维护一个可以跳跃到的最大下标CanIndex,初始为0；
                则CanIndex=Max(CanIndex，i+nums[i]),其中i=0,1,...,CanIndex。通过循环不断更新CanIndex的值，判断是否能等于或大于数组长度-1。
                
                10.跳跃游戏2（到达最后一个下标的最少次数）
                方法：贪心思想。
                方式一：（逆向）为了最少次数到达最后的下标，我们逆向思考，能一步到达最后下标的上一步落点有哪些下标，且为了最小次数，我们应选择能够到达最后下标中的上一步落点中的最小下标；然后寻找能一步到达倒数第二步下标的落点中的最小下标.....
                方式二：（正向）从下标0出发，初始能到达的最远位置记为border=0，只要下标小于等于border的位置我们都可以一步到达，同时记录next_border=max(next_border,i+nums[i])，遍历到border后，如果想走的更远就需要增加步数，即step+1，同时将border更新为next_border,后续继续维护next_border,直到达到最后一个下标。
                
                11.H指数
                方法一：计数排序。其思想是数组元素的范围是确定的。计数数组counter[i]表示数组中等于i的元素个数有多少个，则统计小于i（或者大于i）的元素个数可以使用累加法统计，即可用于排序。
                
                方法二：二分排序。我们需要这样找到一个h，使得它是满足性质（至少有h篇论文的引用次数至少为h）的最大值，使得小于等于h的所有x都满足这个性质。我们可以不断采用二分法不断去逼近，左右边界low=0，high=nums.length;如果当mid满足这个性质（至少有mid篇的引用次数至少为mid），则令low=mid,不满足这个性质时，令high=mid-1；直至循环条件不满足（low<high)，此时low即为我们寻找的h。
                
                12.O(1)时间插入、删除和获取随机元素（等概率）
                方法：哈希表+可变长数组。由于需要满足O（1）获取随机元素这个性质，考虑可变长数组作为存储容器，O（1）实现不重复插入和删除，则需要通过哈希表实现，同时value记录元素的下标。注意的是数组的删除操作是O(n),但是没有要求保持稳定性，可以选择将最后的数组元素与删除元素交换位置（通过哈希表O(1)查到下标）,然后实现O（1）删除。
                
                13.除了自身以外数组的乘积
                方法：用数组L[i]和R[i]通过两次遍历,分别记录下标为i的元素的左边全部元素和右边全部元素的乘积，然后L[i]和R[i]的乘积即为目标mul[i]。
                优化：可以用单个变量value记录右边元素的乘积，这样在遍历右边全部元素乘积的过程中即可统计mul[i]，这样只需要一个数组（返回数组）。
                
                14.加油站
                方法：贪心算法。假设从加油站x出发，最远可以到达加油站y。则根据题目则有
                ， 	，其中
                假设我们可以在加油站x和y之间找到一个加油站z，从加油站z出发可以到达比加油站y更远，但
                
                
                
                故不可能在x和y之间找到一个加油站走的更远，故下一次应该从到达最远加油站y开始继续往后尝试。代码优化：从下标start=0的加油站开始尝试，如果最远到达的加油站的坐标end小于遍历的初始坐标start，则说明无法到达（因为小于start的坐标已经尝试过），可以通过start<len作为跳出循环条件。
                
                15.分发糖果
                方法一：贪心思想。我们尝试把题目的要求（相邻的孩子中，分数较高的孩子获得更高的糖果）化分为满足左规则（ratings[i]>ratings[i-1])和右规则（ratings[i]>ratings[i+1]);以左规则为例，如果下标为i孩子的分数大于下标i-1的孩子的分数，则糖果数Candy_left[i]=Candy_left[i-1]+1，否则Candy_left[i]=1，这样下来所有的孩子能在最少糖果总数下满足左规则要求；同理可以求得满足右规则要求每个孩子的Candy_right[i]，我们取每个孩子满足左规则和右规则的糖果数的较大者，这样即可满足题目要求。
                
                方法二：贪心思想。我们可以注意到若要最少糖果数满足题目要求，当ratings[i]>ratings[i-1],则Candy[i]=Candy[i-1]+1时可以最少糖果总数满足；当ratings[i]<ratings[i-1],我们令Candy[i]=1可以达到总数最少，但可能出现一种异常情况，Candy[i-1]=1导致不满足题目要求，故我们需要把Candy[i-1]+1，若后续又来一个更小值，则前两个都需要+1，因此可以考虑记录单调递减序列的长度dec，每次总糖果增加dec即可，但是当单调序列中的第一个孩子的糖果数等于前一个孩子的糖果数（此时不满足题目要求了），需要把前一个孩子也考虑到单调递减序列中（即dec++）。所以可以记录一个单调递增序列的长度inc(也表示单调递减序列中第一个孩子前一个孩子的糖果数）。
                
                16.接雨水
                方法一：单调栈。分析题目，我们可以知道当形成类似这样的（ⅴ）的坑时，即可以接到水；因此我们可以考虑维护一个单调递减栈（记录柱子的下标），这样栈中元素对应的柱子高度相当于已经形成坑的左侧（栈中的元素还有靠近栈底的邻居），这样当我们遇到比栈顶下标对应的柱子高度更高的柱子时，即可形成一个接水的坑。记遍历到的下标为index，栈顶的下标为top，次栈顶的下标为minor（如果存在），则接到的水量
                water=（index-minor-1）*（min(height[minor],height[index])-height[top])
                
                方法二：动态规划。对于每个下标为i的柱子而言，如果我们能知道其左边的最高柱子高度left_max（涵盖该柱子）,且也能知道其右边的最高柱子高度right_max（涵盖该柱子），则柱子能够接到的水量为min(left_max,right_max)-柱子height；故我们可以用两个数组left_max[i],right_max[i]记录柱子i左边的最高柱子高度和右边的最高柱子高度；其中状态转移方程可化为
                
                
                方法三：双指针。假设有两个指针left=0、right=n-1，left_max维护left左边的最高柱子高度（涵盖该柱子），right_max维护right右边的最高柱子高度（涵盖该柱子）；如果left_max<right_max，则对于下标left的柱子而言，无论后面在其右侧出现比right_max更高的柱子出现时，都被min(left_max,right_max)这个条件排除在外了，即下标为left的柱子接水量已经确定，即可令left++继续遍历；如果left_max>right_max时，对于下标为right的柱子而言，无论后面在其左侧出现比left_max更高的柱子出现时，也被min(left_max,right_max)这个条件排除在外，故下标为right的柱子接水量已经确定，令right--继续遍历。
                
                """,Map.of("source","sample.doc"));


        TokenTextSplitter splitter = TokenTextSplitter.builder()
                .withChunkSize(1000)
                .withMinChunkSizeChars(400)
                .withKeepSeparator(true)
                .withPunctuationMarks(List.of('.', '?', '!', '\n', ';', ':', '。'))
                .build();
        List<Document> splitDocuments = splitter.apply(List.of(doc1));
        System.out.println("document的size:"+splitDocuments.size());
        for (Document doc : splitDocuments) {
            System.out.println("Chunk: " + doc.getText());
            System.out.println("Metadata: " + doc.getMetadata());
        }
    }

}
