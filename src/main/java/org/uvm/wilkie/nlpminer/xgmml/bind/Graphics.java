//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.09 at 06:45:19 PM EST 
//


package org.uvm.wilkie.nlpminer.xgmml.bind;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice minOccurs="0">
 *           &lt;element ref="{http://www.cs.rpi.edu/XGMML}Line"/>
 *           &lt;element ref="{http://www.cs.rpi.edu/XGMML}center"/>
 *         &lt;/choice>
 *         &lt;element ref="{http://www.cs.rpi.edu/XGMML}att" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.cs.rpi.edu/XGMML}bitmap-atts"/>
 *       &lt;attGroup ref="{http://www.cs.rpi.edu/XGMML}object-atts"/>
 *       &lt;attGroup ref="{http://www.cs.rpi.edu/XGMML}graphics-type-att"/>
 *       &lt;attGroup ref="{http://www.cs.rpi.edu/XGMML}line-atts"/>
 *       &lt;attGroup ref="{http://www.cs.rpi.edu/XGMML}point-atts"/>
 *       &lt;attGroup ref="{http://www.cs.rpi.edu/XGMML}arc-atts"/>
 *       &lt;attGroup ref="{http://www.cs.rpi.edu/XGMML}dimension-atts"/>
 *       &lt;attGroup ref="{http://www.cs.rpi.edu/XGMML}external-atts"/>
 *       &lt;attGroup ref="{http://www.cs.rpi.edu/XGMML}text-atts"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "line",
    "center",
    "att"
})
@XmlRootElement(name = "graphics")
public class Graphics {

    @XmlElement(name = "Line")
    protected Line line;
    protected Center center;
    protected List<Att> att;
    @XmlAttribute(name = "background")
    protected String background;
    @XmlAttribute(name = "foreground")
    protected String foreground;
    @XmlAttribute(name = "stipple")
    protected String stipple;
    @XmlAttribute(name = "visible")
    protected BigInteger visible;
    @XmlAttribute(name = "fill")
    protected String fill;
    @XmlAttribute(name = "outline")
    protected String outline;
    @XmlAttribute(name = "anchor")
    protected AnchorType anchor;
    @XmlAttribute(name = "type")
    protected TypeGraphicsType type;
    @XmlAttribute(name = "width")
    protected BigInteger width;
    @XmlAttribute(name = "arrow")
    protected ArrowType arrow;
    @XmlAttribute(name = "capstyle")
    protected CapstyleType capstyle;
    @XmlAttribute(name = "joinstyle")
    protected JoinstyleType joinstyle;
    @XmlAttribute(name = "smooth")
    protected BigInteger smooth;
    @XmlAttribute(name = "splinesteps")
    protected BigInteger splinesteps;
    @XmlAttribute(name = "x")
    protected Double x;
    @XmlAttribute(name = "y")
    protected Double y;
    @XmlAttribute(name = "z")
    protected Double z;
    @XmlAttribute(name = "extent")
    protected BigInteger extent;
    @XmlAttribute(name = "start")
    protected BigInteger start;
    @XmlAttribute(name = "style")
    protected ArcstyleType style;
    @XmlAttribute(name = "w")
    protected Double w;
    @XmlAttribute(name = "h")
    protected Double h;
    @XmlAttribute(name = "d")
    protected Double d;
    @XmlAttribute(name = "image")
    protected String image;
    @XmlAttribute(name = "bitmap")
    protected String bitmap;
    @XmlAttribute(name = "justify")
    protected JustifyType justify;
    @XmlAttribute(name = "font")
    protected String font;

    /**
     * Gets the value of the line property.
     * 
     * @return
     *     possible object is
     *     {@link Line }
     *     
     */
    public Line getLine() {
        return line;
    }

    /**
     * Sets the value of the line property.
     * 
     * @param value
     *     allowed object is
     *     {@link Line }
     *     
     */
    public void setLine(Line value) {
        this.line = value;
    }

    /**
     * Gets the value of the center property.
     * 
     * @return
     *     possible object is
     *     {@link Center }
     *     
     */
    public Center getCenter() {
        return center;
    }

    /**
     * Sets the value of the center property.
     * 
     * @param value
     *     allowed object is
     *     {@link Center }
     *     
     */
    public void setCenter(Center value) {
        this.center = value;
    }

    /**
     * Gets the value of the att property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the att property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAtt().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Att }
     * 
     * 
     */
    public List<Att> getAtt() {
        if (att == null) {
            att = new ArrayList<Att>();
        }
        return this.att;
    }

    /**
     * Gets the value of the background property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBackground() {
        return background;
    }

    /**
     * Sets the value of the background property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBackground(String value) {
        this.background = value;
    }

    /**
     * Gets the value of the foreground property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getForeground() {
        return foreground;
    }

    /**
     * Sets the value of the foreground property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setForeground(String value) {
        this.foreground = value;
    }

    /**
     * Gets the value of the stipple property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStipple() {
        return stipple;
    }

    /**
     * Sets the value of the stipple property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStipple(String value) {
        this.stipple = value;
    }

    /**
     * Gets the value of the visible property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getVisible() {
        return visible;
    }

    /**
     * Sets the value of the visible property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setVisible(BigInteger value) {
        this.visible = value;
    }

    /**
     * Gets the value of the fill property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFill() {
        return fill;
    }

    /**
     * Sets the value of the fill property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFill(String value) {
        this.fill = value;
    }

    /**
     * Gets the value of the outline property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutline() {
        return outline;
    }

    /**
     * Sets the value of the outline property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutline(String value) {
        this.outline = value;
    }

    /**
     * Gets the value of the anchor property.
     * 
     * @return
     *     possible object is
     *     {@link AnchorType }
     *     
     */
    public AnchorType getAnchor() {
        return anchor;
    }

    /**
     * Sets the value of the anchor property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnchorType }
     *     
     */
    public void setAnchor(AnchorType value) {
        this.anchor = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link TypeGraphicsType }
     *     
     */
    public TypeGraphicsType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeGraphicsType }
     *     
     */
    public void setType(TypeGraphicsType value) {
        this.type = value;
    }

    /**
     * Gets the value of the width property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getWidth() {
        return width;
    }

    /**
     * Sets the value of the width property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setWidth(BigInteger value) {
        this.width = value;
    }

    /**
     * Gets the value of the arrow property.
     * 
     * @return
     *     possible object is
     *     {@link ArrowType }
     *     
     */
    public ArrowType getArrow() {
        return arrow;
    }

    /**
     * Sets the value of the arrow property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrowType }
     *     
     */
    public void setArrow(ArrowType value) {
        this.arrow = value;
    }

    /**
     * Gets the value of the capstyle property.
     * 
     * @return
     *     possible object is
     *     {@link CapstyleType }
     *     
     */
    public CapstyleType getCapstyle() {
        return capstyle;
    }

    /**
     * Sets the value of the capstyle property.
     * 
     * @param value
     *     allowed object is
     *     {@link CapstyleType }
     *     
     */
    public void setCapstyle(CapstyleType value) {
        this.capstyle = value;
    }

    /**
     * Gets the value of the joinstyle property.
     * 
     * @return
     *     possible object is
     *     {@link JoinstyleType }
     *     
     */
    public JoinstyleType getJoinstyle() {
        return joinstyle;
    }

    /**
     * Sets the value of the joinstyle property.
     * 
     * @param value
     *     allowed object is
     *     {@link JoinstyleType }
     *     
     */
    public void setJoinstyle(JoinstyleType value) {
        this.joinstyle = value;
    }

    /**
     * Gets the value of the smooth property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSmooth() {
        return smooth;
    }

    /**
     * Sets the value of the smooth property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSmooth(BigInteger value) {
        this.smooth = value;
    }

    /**
     * Gets the value of the splinesteps property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSplinesteps() {
        return splinesteps;
    }

    /**
     * Sets the value of the splinesteps property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSplinesteps(BigInteger value) {
        this.splinesteps = value;
    }

    /**
     * Gets the value of the x property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getX() {
        return x;
    }

    /**
     * Sets the value of the x property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setX(Double value) {
        this.x = value;
    }

    /**
     * Gets the value of the y property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getY() {
        return y;
    }

    /**
     * Sets the value of the y property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setY(Double value) {
        this.y = value;
    }

    /**
     * Gets the value of the z property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getZ() {
        return z;
    }

    /**
     * Sets the value of the z property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setZ(Double value) {
        this.z = value;
    }

    /**
     * Gets the value of the extent property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getExtent() {
        return extent;
    }

    /**
     * Sets the value of the extent property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setExtent(BigInteger value) {
        this.extent = value;
    }

    /**
     * Gets the value of the start property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getStart() {
        return start;
    }

    /**
     * Sets the value of the start property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setStart(BigInteger value) {
        this.start = value;
    }

    /**
     * Gets the value of the style property.
     * 
     * @return
     *     possible object is
     *     {@link ArcstyleType }
     *     
     */
    public ArcstyleType getStyle() {
        return style;
    }

    /**
     * Sets the value of the style property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArcstyleType }
     *     
     */
    public void setStyle(ArcstyleType value) {
        this.style = value;
    }

    /**
     * Gets the value of the w property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getW() {
        return w;
    }

    /**
     * Sets the value of the w property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setW(Double value) {
        this.w = value;
    }

    /**
     * Gets the value of the h property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getH() {
        return h;
    }

    /**
     * Sets the value of the h property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setH(Double value) {
        this.h = value;
    }

    /**
     * Gets the value of the d property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getD() {
        return d;
    }

    /**
     * Sets the value of the d property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setD(Double value) {
        this.d = value;
    }

    /**
     * Gets the value of the image property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the value of the image property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImage(String value) {
        this.image = value;
    }

    /**
     * Gets the value of the bitmap property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBitmap() {
        return bitmap;
    }

    /**
     * Sets the value of the bitmap property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBitmap(String value) {
        this.bitmap = value;
    }

    /**
     * Gets the value of the justify property.
     * 
     * @return
     *     possible object is
     *     {@link JustifyType }
     *     
     */
    public JustifyType getJustify() {
        return justify;
    }

    /**
     * Sets the value of the justify property.
     * 
     * @param value
     *     allowed object is
     *     {@link JustifyType }
     *     
     */
    public void setJustify(JustifyType value) {
        this.justify = value;
    }

    /**
     * Gets the value of the font property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFont() {
        return font;
    }

    /**
     * Sets the value of the font property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFont(String value) {
        this.font = value;
    }

}
