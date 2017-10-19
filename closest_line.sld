<?xml version="1.0" encoding="UTF-8"?>
<StyledLayerDescriptor version="1.0.0" xmlns="http://www.opengis.net/sld"
  xmlns:ogc="http://www.opengis.net/ogc" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://www.opengis.net/sld http://schemas.opengis.net/sld/1.0.0/StyledLayerDescriptor.xsd">
 <NamedLayer><Name>Point with rotated label</Name>
 <UserStyle><Title>GeoServer SLD Cook Book: Point with rotated label</Title>
 <FeatureTypeStyle><Rule><PointSymbolizer><Graphic><Mark>
 <WellKnownName>circle</WellKnownName>
 
 <Stroke>
               <CssParameter name="stroke">#000000</CssParameter>
               <CssParameter name="stroke-width">2</CssParameter>
             </Stroke>
 
 </Mark><Size>30</Size>
 </Graphic></PointSymbolizer>
 <TextSymbolizer><Label><ogc:PropertyName>jeton</ogc:PropertyName>
 </Label><Font><CssParameter name="font-family">Arial</CssParameter>
 <CssParameter name="font-size">12</CssParameter><CssParameter name="font-style">normal</CssParameter>
 <CssParameter name="font-weight">bold</CssParameter></Font><LabelPlacement>
 <PointPlacement><AnchorPoint><AnchorPointX>0.5</AnchorPointX><AnchorPointY>0.0</AnchorPointY>
 </AnchorPoint><Displacement><DisplacementX>0</DisplacementX><DisplacementY>25</DisplacementY>
 </Displacement><Rotation>-45</Rotation></PointPlacement></LabelPlacement><Fill>
 <CssParameter name="fill">#990099</CssParameter></Fill></TextSymbolizer></Rule>
 </FeatureTypeStyle></UserStyle></NamedLayer></StyledLayerDescriptor>