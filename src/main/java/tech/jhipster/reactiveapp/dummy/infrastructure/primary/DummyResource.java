package tech.jhipster.reactiveapp.dummy.infrastructure.primary;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jhipster.reactiveapp.dummy.application.DummyApplicationService;
import tech.jhipster.reactiveapp.dummy.infrastructure.primary.dto.DummyDTO;

@RestController
@RequestMapping("/api/dummy")
public class DummyResource {

    private final DummyApplicationService dummyApplicationService;

    public DummyResource(DummyApplicationService dummyApplicationService) {
        this.dummyApplicationService = dummyApplicationService;
    }

    @GetMapping
    public Flux<DummyDTO> list() {
        return dummyApplicationService.list();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<DummyDTO> add(@RequestBody DummyDTO dummyDTO) {
        return dummyApplicationService.add(dummyDTO);
    }

    @PutMapping("/{name}")
    public Mono<DummyDTO> update(@PathVariable String name) {
        return dummyApplicationService.incrementCount(name);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> clear() {
        return dummyApplicationService.clear();
    }
}
