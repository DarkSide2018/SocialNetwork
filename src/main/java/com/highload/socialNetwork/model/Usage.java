package com.highload.socialNetwork.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor
public class Usage {
    private int cpuUsage;
    private int memoryUsage;
    private Date date;
}
