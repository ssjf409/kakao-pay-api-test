package com.kakao.membership.controller.point;

import com.kakao.membership.application.point.PointApplication;
import com.kakao.membership.controller.dto.membership.PointChangeRequest;
import com.kakao.membership.domain.response.Response;
import com.kakao.membership.service.dto.PointDto;
import com.kakao.membership.service.dto.PointHistoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/point")
@RestController
public class PointController {

    private final PointApplication pointApplication;

    @PostMapping("/earn")
    public Response<PointDto> earnPoint(@Valid @RequestBody PointChangeRequest request) {
        return Response.ok(pointApplication.changePoint(request));
    }

    @PostMapping("/use")
    public Response<PointDto> usePoint(@Valid @RequestBody PointChangeRequest request) {
        return Response.ok(pointApplication.changePoint(request));
    }

    @GetMapping("/history")
    public Response<List<PointHistoryDto>> searchHistory(@RequestParam String barcode,
                                                         @RequestParam @DateTimeFormat(iso = ISO.DATE_TIME)
                                                                 LocalDateTime startDate,
                                                         @RequestParam @DateTimeFormat(iso = ISO.DATE_TIME)
                                                                 LocalDateTime endDate) {
        return Response.ok(pointApplication.searchHistory(barcode, startDate, endDate));
    }
}
