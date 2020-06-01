package vip.bblog.cunadmin.modules.es.controller;

import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="1396513066@qq.com">Yu Yong</a>
 * @version 1.0
 * @date 2020年05月28日 16:57
 * @desc EsController
 */
@RestController
@RequestMapping("/test/order")
public class EsController {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    /*@GetMapping
    public PageResult<List<OrderVO>> listPageResult(PageParams pageParams) {
        BoolQueryBuilder monthAggQuery = QueryBuilders.boolQuery();
        monthAggQuery.must(QueryBuilders.rangeQuery("createTime").gte(LocalDateTime.of(LocalDate.now(), LocalTime.MIN).with(TemporalAdjusters.firstDayOfYear()))
                .lte(LocalDateTime.of(LocalDate.now(), LocalTime.MAX).with(TemporalAdjusters.lastDayOfYear())));
        DateHistogramAggregationBuilder monthAgg = AggregationBuilders.dateHistogram("month_order")
                .field("createTime")
                .fixedInterval(DateHistogramInterval.MONTH);
        NativeSearchQueryBuilder builder1 = new NativeSearchQueryBuilder()
                .withQuery(monthAggQuery)
                .withPageable(PageRequest.of(0, 1))
                .addAggregation(monthAgg);
        SearchHits<OrderVO> search8 = elasticsearchRestTemplate.search(builder1.build(), OrderVO.class);
        Aggregations aggregations = search8.getAggregations();
        assert aggregations != null;
        Map<String, Aggregation> stringAggregationMap = aggregations.asMap();
        return PageResult.success();
    }
*/
    private HighlightBuilder getHighlightBuilder() {
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        HighlightBuilder.Field highlightTitle = new HighlightBuilder.Field("senderName");
        highlightTitle.preTags("<span style='color:red'>");
        highlightTitle.postTags("</span>");
        highlightBuilder.field(highlightTitle);
        return highlightBuilder;
    }

}
