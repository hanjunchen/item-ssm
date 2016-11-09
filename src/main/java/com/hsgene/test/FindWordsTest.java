package com.hsgene.test;

import com.tfc.analysis.KWSeeker;
import com.tfc.analysis.entity.Keyword;
import com.tfc.analysis.fragment.HTMLFragment;
import com.tfc.analysis.process.WordFinder;
import org.junit.Test;

/**
 * Created by hjc on 2016/11/9.
 */
public class FindWordsTest {

    @Test
    public void searchWordsTest(){
        KWSeeker kw1 = KWSeeker.getInstance(new Keyword("test1"), new Keyword("test2"));
        // 添加一个词
        kw1.addWord(new Keyword("test3"));
        // 使用默认的高亮方式将文本中含有上面指定的所有词显示出来！
        kw1.highlight("这是test1,要注意哦！");
        // 使用HTML页面加粗的高亮方式将文本中含有上面指定的所有词显示出来！
        kw1.highlight("这是test1,要注意哦！", new HTMLFragment("<b>", "</b>"));
        // 找出文本中所有含有上面词库中的词！
        kw1.findWords("这是test1,要注意哦！");
        // 使用指定的processor（如：WordFinder找出文本中所有含有上面词库中的词）对文本进行处理！
        kw1.process(new WordFinder(), "这是test1,要注意哦！", null);
    }
}
