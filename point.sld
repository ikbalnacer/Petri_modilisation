<?xml version="1.0" encoding="UTF-8"?>
<StyledLayerDescriptor version="1.0.0" xmlns="http://www.opengis.net/sld"
  xmlns:ogc="http://www.opengis.net/ogc" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://www.opengis.net/sld http://schemas.opengis.net/sld/1.0.0/StyledLayerDescriptor.xsd">
  <NamedLayer>

    <Name>Grass</Name>
    <UserStyle>
      <FeatureTypeStyle>
        <Rule>
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
          <PointSymbolizer>
     <Graphic>
       <Mark>
         <WellKnownName>ttf://Webdings#0xE3D9</WellKnownName>
         <Fill>
           <CssParameter name="fill">#AAAAAA</CssParameter>
         </Fill>
         <Stroke/>
       </Mark>
     <Size>40</Size>
   </Graphic>
 </PointSymbolizer>
  <TextSymbolizer>
         <Label>
           <ogc:PropertyName>Name</ogc:PropertyName>
         </Label>
         <Fill>
           <CssParameter name="fill">#000000</CssParameter>
         </Fill>
       </TextSymbolizer>
        </Rule>
      </FeatureTypeStyle>
    </UserStyle>
  </NamedLayer>
</StyledLayerDescriptor>