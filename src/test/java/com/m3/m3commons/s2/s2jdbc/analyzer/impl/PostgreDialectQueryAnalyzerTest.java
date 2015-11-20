package com.m3.m3commons.s2.s2jdbc.analyzer.impl;

import com.m3.m3commons.s2.s2jdbc.analyzer.QueryAnalyzer;
import com.m3.m3commons.s2.s2jdbc.analyzer.exception.QueryAnalyzerWarning;
import com.m3.m3commons.s2.unit.SimpleS2TestCase;

public class PostgreDialectQueryAnalyzerTest extends SimpleS2TestCase {

    public void test_analyze_A$String_throwsWarning_selectAll() throws Exception {
        QueryAnalyzer target = autoBind(new PostgreDialectQueryAnalyzer());
        String sql = "select * from admin_user";
        try {
            target.analyze(sql);
            fail();
        } catch (QueryAnalyzerWarning e) {
            e.printStackTrace();
        }
    }

    public void test_analyze_A$String_nextVal() throws Exception {
        QueryAnalyzer target = autoBind(new PostgreDialectQueryAnalyzer());
        String sql = "select nextval('admin_user_id')";
        target.analyze(sql);
    }

    public void test_analyze_A$String_enable_seqscan_issue() throws Exception {
        QueryAnalyzer target = autoBind(new PostgreDialectQueryAnalyzer());
        String sql = "select * from mobile_inquiry where first_code = 'a'";
        target.analyze(sql);
    }

    public void test_analyze_A$String_innerJoin() throws Exception {
        QueryAnalyzer target = autoBind(new PostgreDialectQueryAnalyzer());
        String sql = "select * from member as m inner join tweet as t on m.id = t.member_id limit 1";
        target.analyze(sql);
    }

    public void test_analyze_A$String_notSelectSQL() throws Exception {
        QueryAnalyzer target = autoBind(new PostgreDialectQueryAnalyzer());
        String sql = "insert into task_entity values (9999,\"dummy\",false,\"{}\",now(),now());";
        target.analyze(sql);
    }

    public void test_analyze_A$String_loggingOnly() throws Exception {
        QueryAnalyzer target = autoBind(new PostgreDialectQueryAnalyzer());
        String sql = "select * from admin_user";
        target.setLoggingOnly(true);
        target.analyze(sql);
    }
    
}
