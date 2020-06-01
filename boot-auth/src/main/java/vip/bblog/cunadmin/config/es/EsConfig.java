package vip.bblog.cunadmin.config.es;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.elasticsearch.config.ElasticsearchConfigurationSupport;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchCustomConversions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * @author <a href="zyc199777@gmail.com">Zhu Yichen</a>
 * @version 1.0
 * @date 2019年12月18日 10:50
 * @desc EsConfig
 */
@Slf4j
@Configuration
public class EsConfig extends ElasticsearchConfigurationSupport {

    @Bean
    @Override
    public ElasticsearchCustomConversions elasticsearchCustomConversions() {
        return new ElasticsearchCustomConversions(
                Arrays.asList(new LocalDateTimeToString(),
                        new LocalDateToString(),
                        new LocalTimeToString(),
                        new StringToLocalDateTime(),
                        new StringToLocalDate(),
                        new StringToLocalTime(),
                        new DateToString(),
                        new StringToDate(),
                        new MapToString()
                ));
    }

    @WritingConverter
    static class LocalDateTimeToString implements Converter<LocalDateTime, String> {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        @Override
        public String convert(@NonNull LocalDateTime source) {
            try {
                return dtf.format(source);
            } catch (Exception e) {
                log.error("{} ,LocalDateTime -> String 失败", source);
                return null;
            }
        }
    }

    @ReadingConverter
    static class StringToLocalDateTime implements Converter<String, LocalDateTime> {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter dtf1 = DateTimeFormatter.ISO_DATE_TIME;

        @Override
        public LocalDateTime convert(@NonNull String source) {
            try {
                return LocalDateTime.parse(source, dtf);
            } catch (Exception e) {
                try {
                    return LocalDateTime.parse(source, dtf1);
                } catch (Exception e1) {
                    log.error("{} ,String -> LocalDateTime 失败", source);
                    return null;
                }
            }
        }
    }

    @WritingConverter
    static class LocalDateToString implements Converter<LocalDate, String> {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        @Override
        public String convert(@NonNull LocalDate source) {
            try {
                return dtf.format(source);
            } catch (Exception e) {
                log.error("{} ,LocalDate -> String 失败", source);
                return null;
            }
        }
    }

    @ReadingConverter
    static class StringToLocalDate implements Converter<String, LocalDate> {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        @Override
        public LocalDate convert(@NonNull String source) {
            try {
                return LocalDate.parse(source, dtf);
            } catch (Exception e) {
                log.error("{} ,String -> LocalDate 失败", source);
                return null;
            }
        }
    }

    @WritingConverter
    static class LocalTimeToString implements Converter<LocalTime, String> {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

        @Override
        public String convert(@NonNull LocalTime source) {
            try {
                return dtf.format(source);
            } catch (Exception e) {
                log.error("{} ,LocalTime -> String 失败", source);
                return null;
            }
        }
    }

    @ReadingConverter
    static class StringToLocalTime implements Converter<String, LocalTime> {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

        @Override
        public LocalTime convert(@NonNull String source) {
            try {
                return LocalTime.parse(source, dtf);
            } catch (Exception e) {
                log.error("{} ,String -> LocalTime 失败", source);
                return null;
            }
        }
    }

    @WritingConverter
    static class DateToString implements Converter<Date, String> {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        @Override
        public String convert(@NonNull Date source) {
            try {
                return df.format(source);
            } catch (Exception e) {
                log.error("{} ,Date -> String 失败", source);
                return null;
            }
        }
    }

    @ReadingConverter
    static class StringToDate implements Converter<String, Date> {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;

        @Override
        public Date convert(@NonNull String source) {
            try {
                return df.parse(source);
            } catch (ParseException e1) {
                try {
                    LocalDateTime time = LocalDateTime.from(dtf.parse(source));
                    return Date.from(time.atZone(ZoneId.of("GMT+8")).toInstant());
                } catch (Exception e2) {
                    log.error("{} ,String -> Date 失败", source);
                }
                return null;
            }
        }
    }

    @ReadingConverter
    static class MapToString implements Converter<Map<String, Object>, String> {
        @Override
        public String convert(@NonNull Map<String, Object> source) {
            try {
                String lon = source.get("lon").toString();
                String lat = source.get("lat").toString();
                return String.format("%s,%s", lon, lat);
            } catch (Exception e) {
                log.error("{} ,Map -> GEO 失败", source);
                return "";
            }
        }
    }
}
