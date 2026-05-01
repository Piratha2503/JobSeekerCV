package com.cvmaker.Jobscorecv.Domains.CV.Services;

import com.cvmaker.Jobscorecv.Common.APIResponse.PaginatedResponse;
import com.cvmaker.Jobscorecv.Domains.CV.DTOs.RequestDTOs.CVCreateRequest;
import com.cvmaker.Jobscorecv.Domains.CV.DTOs.RequestDTOs.CVUpdateRequest;
import com.cvmaker.Jobscorecv.Domains.CV.DTOs.ResponsetDTOs.CVResponse;
import com.cvmaker.Jobscorecv.Domains.CV.Entities.CV;
import com.cvmaker.Jobscorecv.Domains.CV.Repositories.CVRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CVServiceImpl implements CVService {

    private final CVRepository repository;

    @Override
    public CVResponse create(CVCreateRequest request) {

        CV cv = CVCreateRequest.toEntity(request);

        return CVResponse.map(repository.save(cv));
    }

    @Override
    public CVResponse update(Long id, CVUpdateRequest request) {

        // 🔥 JOIN FETCH பயன்படுத்துறோம்
        CV cv = repository.findByIdWithAll(id)
                .orElseThrow(() -> new RuntimeException("CV not found"));

        CVUpdateRequest.updateEntity(cv, request);

        return CVResponse.map(repository.save(cv));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public CVResponse getById(Long id) {

        // 🔥 NO N+1
        CV cv = repository.findByIdWithAll(id)
                .orElseThrow(() -> new RuntimeException("CV not found"));

        return CVResponse.map(cv);
    }

    @Override
    public List<CVResponse> getAll(PaginatedResponse.Pagination pagination) {

        Sort sort = pagination.getOrderedBy().equalsIgnoreCase("asc") ?
                Sort.by(pagination.getSortedBy()).ascending() :
                Sort.by(pagination.getSortedBy()).descending();

        Pageable pageable = PageRequest.of(
                pagination.getPageNumber(),
                pagination.getPageSize(),
                sort
        );

        Page<CV> page = repository.findAll(pageable);

        pagination.setTotalPages(page.getTotalPages());
        pagination.setTotalRecords(page.getTotalElements());

        return page.getContent()
                .stream()
                .map(CVResponse::map)
                .toList();
    }
}
