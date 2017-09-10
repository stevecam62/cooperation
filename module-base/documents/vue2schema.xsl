<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsd="http://www.w3.org/2001/XMLSchema"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">

	<xsl:template match="/">
		<xsd:schema targetNamespace="http://www.example.org/OneIdSchema"
			xmlns:tns="http://www.example.org/OneIdSchema" elementFormDefault="qualified">
			<xsl:apply-templates />
		</xsd:schema>
	</xsl:template>

	<xsl:template match="child[@xsi:type='node']">
		<xsd:complexType>
			<xsl:attribute name="name"><xsl:value-of
				select="translate(@label,' ','')" /></xsl:attribute>
			<xsl:variable name="id">
				<xsl:value-of select="@ID" />
			</xsl:variable>
			<xsd:sequence>
				<xsd:element name="name" type="xsd:string" minOccurs="1" maxOccurs="1"/>
				<xsl:for-each
					select="//child[@xsi:type='link']/ID1[@xsi:type='node'][text()=$id]">
					<xsl:variable name="ref_id">
						<xsl:value-of select="../ID2" />
					</xsl:variable>
					<xsl:for-each select="//child[@xsi:type='node'][@ID=$ref_id]">
					<xsl:variable name="name">
						<xsl:value-of select="translate(@label,' ','')" />
					</xsl:variable>
						<xsd:element>
							<xsl:attribute name="name"><xsl:value-of
								select="concat(lower-case(substring($name,1,1)),
             substring($name,2))" /></xsl:attribute>
							<xsl:attribute name="type">tns:<xsl:value-of
								select="$name" /></xsl:attribute>
							<xsl:attribute name="minOccurs">1</xsl:attribute>
							<xsl:attribute name="maxOccurs">1</xsl:attribute>
						</xsd:element>
					</xsl:for-each>
				</xsl:for-each>
			</xsd:sequence>
		</xsd:complexType>
	</xsl:template>

	<xsl:template match="node()|@*">
		<xsl:apply-templates select="node()|@*" />
	</xsl:template>

</xsl:stylesheet> 