package org.openskope.paleocarbrowser.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.when;

import static org.junit.Assert.assertEquals;
import org.openskope.paleocarbrowser.model.PaleocarBrowserConfig;
import org.openskope.paleocarbrowser.service.PaleocarBrowserConfigService;

@RunWith(MockitoJUnitRunner.class)
public class PaleocarBrowserConfigControllerTest {

    @InjectMocks
    private PaleocarBrowserConfigController configController;

    @Mock
    private PaleocarBrowserConfigService configService;

    @Test
    public void getConfigReturnsConfigProvidedByConfigService() {
        PaleocarBrowserConfig expectedConfig = new PaleocarBrowserConfig();
        when(configService.getConfig()).thenReturn(expectedConfig);
        assertSame(expectedConfig, configController.getConfig());
    }
}