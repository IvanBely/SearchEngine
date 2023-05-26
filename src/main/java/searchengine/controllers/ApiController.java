package searchengine.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import searchengine.dto.SearchDto;
import searchengine.dto.response.ResultDto;
import searchengine.dto.statistics.StatisticsResponse;
import searchengine.repository.SiteRepository;
import searchengine.search.SearchStarter;
import searchengine.services.IndexingService;
import searchengine.services.StatisticsService;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public record ApiController(StatisticsService statisticsService, IndexingService indexingService, SiteRepository siteRepository, SearchStarter searchStarter) {

    @GetMapping("/statistics")
    public ResponseEntity<StatisticsResponse> getStatistics() {
        return ResponseEntity.ok(statisticsService.getStatisticsResponse());
    }
    
    @GetMapping("/startIndexing")
    public ResultDto startIndexing() {
        return indexingService.startIndexing();
    }
    
    @GetMapping("/stopIndexing")
    public ResultDto stopIndexing() {
        log.info("ОСТАНОВКА ИНДЕКСАЦИИ");
        return indexingService.stopIndexing();
    }

    @PostMapping("/indexPage")
    public ResultDto indexPage(@RequestParam(name = "url") String url) {
        if (url.isEmpty()) {
            log.info("Страница не указана");
            return new ResultDto(false, "Страница не указана", HttpStatus.BAD_REQUEST);
        } else {
            if (indexingService.indexPage(url) == true) {
                log.info("Страница - " + url + " - добавлена на переиндексацию");
                return new ResultDto(true, HttpStatus.OK);
            } else {
                log.info("Указанная страница" + "за пределами конфигурационного файла");
                return new ResultDto(false, "Указанная страница" + "за пределами конфигурационного файла", HttpStatus.BAD_REQUEST);
            }
        }
    }

    @GetMapping("/search")
    public ResultDto search(@RequestParam(name = "query", required = false, defaultValue = "") String query,
                            @RequestParam(name = "site", required = false, defaultValue = "") String site,
                            @RequestParam(name = "offset", required = false, defaultValue = "0") int offset) {
        List<SearchDto> searchData;
        if (!site.isEmpty()) {
            if (siteRepository.findByUrl(site) == null) {

                return new ResultDto(false, "Данная страница находится за пределами сайтов,\n" +
                        "указанных в конфигурационном файле", HttpStatus.BAD_REQUEST) ;
            } else {
                searchData = searchStarter.getSearchFromOneSite(query, site, offset, 30);
            }
        } else {
            searchData = searchStarter.getFullSearch(query, offset, 30);
        }
        return new ResultDto(true, searchData.size(), searchData, HttpStatus.OK);
    }
}
