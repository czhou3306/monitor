package com.qiangungun.monitor.biz.shared.helper;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.qiangungun.monitor.biz.shared.helper.operator.AddOperator;
import com.qiangungun.monitor.biz.shared.helper.operator.DivOperator;
import com.qiangungun.monitor.biz.shared.helper.operator.MulOperator;
import com.qiangungun.monitor.biz.shared.helper.operator.SubOperator;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.IExpressContext;

@Service("qlExpressUtil")
public class QlExpressUtil implements ApplicationContextAware {

    private static ExpressRunner runner;
    static {
        runner = new ExpressRunner();
    }
    private static boolean       isInitialRunner = false;
    private ApplicationContext   applicationContext;     // spring上下文

    /**
     * 
     * @param statement
     *            执行语句
     * @param context
     *            上下文
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public Object execute(String statement, Map<String, Object> context) throws Exception {
        initRunner(runner);
        IExpressContext expressContext = new QLExpressContext(context, applicationContext);
        statement = initStatement(statement);
        return runner.execute(statement, expressContext, null, true, false);
    }

    /**
     * 在此处把一些中文符号替换成英文符号
     * 
     * @param statement
     * @return
     */
    private String initStatement(String statement) {
        return statement.replace("（", "(").replace("）", ")").replace("；", ";").replace("，", ",")
            .replace("“", "\"").replace("”", "\"");
    }

    private void initRunner(ExpressRunner runner) {
        if (isInitialRunner == true) {
            return;
        }
        synchronized (runner) {
            if (isInitialRunner == true) {
                return;
            }
            try {
                runner.addOperator("ADD", new AddOperator());
                runner.addOperator("SUB", new SubOperator());
                runner.addOperator("MUL", new MulOperator());
                runner.addOperator("DIV", new DivOperator());
            } catch (Exception e) {
                throw new RuntimeException("初始化失败表达式", e);
            }
        }
        isInitialRunner = true;
    }

    public void setApplicationContext(ApplicationContext aContext) throws BeansException {
        applicationContext = aContext;
    }

}