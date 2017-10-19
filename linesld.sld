<?xml version="1.0" encoding="ISO-8859-1"?>
<StyledLayerDescriptor version="1.0.0" 
        xsi:schemaLocation="http://www.opengis.net/sld StyledLayerDescriptor.xsd" 
        xmlns="http://www.opengis.net/sld" 
        xmlns:ogc="http://www.opengis.net/ogc" 
        xmlns:xlink="http://www.w3.org/1999/xlink" 
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    <NamedLayer>
        <Name>Blue arrows</Name>
        <UserStyle>
            <Title>A blue line with end arrows</Title>
            <FeatureTypeStyle>
                <Rule>
                    <Name>Blue_Arrow_Line</Name>
                     <ogc:Filter>
       <And> <PropertyIsNotEqualTo>
           <PropertyName>id</PropertyName>
            <Literal>1</Literal>
          
        </PropertyIsNotEqualTo>
        <PropertyIsNotEqualTo>
          
           <PropertyName>id</PropertyName>
            <Literal>2</Literal>
        </PropertyIsNotEqualTo></And>
       </ogc:Filter>
                    <LineSymbolizer>
                        <Stroke>
                            <CssParameter name="stroke">#0000FF</CssParameter>
                            <CssParameter name="stroke-width">2</CssParameter>
                        </Stroke>
                    </LineSymbolizer>
                    <PointSymbolizer>
                        <Geometry>
                            <ogc:Function name="endPoint">
                                <ogc:PropertyName>the_geom</ogc:PropertyName>
                            </ogc:Function>
                        </Geometry>
                        <Graphic>
                            <Mark>
                                <WellKnownName>shape://oarrow</WellKnownName>
                                <Fill>
                                <CssParameter name="fill">#0000FF</CssParameter>
                                <CssParameter name="fill-opacity">0.5</CssParameter>
                                </Fill>
                                <Stroke>
                                    <CssParameter name="stroke">#0000FF</CssParameter>
                                    <CssParameter name="stroke-width">2</CssParameter>
                                </Stroke>
                            </Mark>
                            <Size>30</Size>
                            <Rotation>
                                <ogc:Function name="endAngle">
                                    <ogc:PropertyName>the_geom</ogc:PropertyName>
                                </ogc:Function>
                            </Rotation>
                        </Graphic>
                    </PointSymbolizer>
                    <TextSymbolizer><Label><ogc:PropertyName>numero</ogc:PropertyName>
 </Label><Font><CssParameter name="font-family">Arial</CssParameter>
 <CssParameter name="font-size">12</CssParameter><CssParameter name="font-style">normal</CssParameter>
 <CssParameter name="font-weight">bold</CssParameter></Font><LabelPlacement>
 <PointPlacement><AnchorPoint><AnchorPointX>0.5</AnchorPointX><AnchorPointY>0.0</AnchorPointY>
 </AnchorPoint><Displacement><DisplacementX>0</DisplacementX><DisplacementY>25</DisplacementY>
 </Displacement><Rotation>-45</Rotation></PointPlacement></LabelPlacement><Fill>
 <CssParameter name="fill">#990099</CssParameter></Fill></TextSymbolizer>
                </Rule>
            </FeatureTypeStyle>
        </UserStyle>
    </NamedLayer>
</StyledLayerDescriptor>