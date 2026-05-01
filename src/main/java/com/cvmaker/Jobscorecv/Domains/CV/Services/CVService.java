package com.cvmaker.Jobscorecv.Domains.CV.Services;

import com.cvmaker.Jobscorecv.Common.APIResponse.PaginatedResponse;
import com.cvmaker.Jobscorecv.Domains.CV.DTOs.RequestDTOs.CVCreateRequest;
import com.cvmaker.Jobscorecv.Domains.CV.DTOs.RequestDTOs.CVUpdateRequest;
import com.cvmaker.Jobscorecv.Domains.CV.DTOs.ResponsetDTOs.CVResponse;

import java.util.List;

public interface CVService {

    CVResponse create(CVCreateRequest request);

    CVResponse update(Long id, CVUpdateRequest request);

    void delete(Long id);

    CVResponse getById(Long id);

    List<CVResponse> getAll(PaginatedResponse.Pagination pagination);
}
