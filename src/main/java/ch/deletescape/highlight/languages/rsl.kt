package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = RenderMan RSL
Author = Konstantin Evdokimenko <qewerty@gmail.com>
Contributors = Shuen-Huei Guan <drake.guan@gmail.com>
Category = graphics
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/rsl.js MD5 <6147db3620d9b6d67e960e364116d332>
 */
internal fun rsl(): Mode {
    return Mode(
        keywords = listOf(
            keyword(
                className = "keyword",
                value =
                    "float color point normal vector matrix while for if do return else break extern continue"
            ),
            keyword(
                className = "built_in",
                value =
                    "abs acos ambient area asin atan atmosphere attribute calculatenormal ceil cellnoise clamp comp concat cos degrees depth Deriv diffuse distance Du Dv environment exp faceforward filterstep floor format fresnel incident length lightsource log match max min mod noise normalize ntransform opposite option phong pnoise pow printf ptlined radians random reflect refract renderinfo round setcomp setxcomp setycomp setzcomp shadow sign sin smoothstep specular specularbrdf spline sqrt step tan texture textureinfo trace transform vtransform xcomp ycomp zcomp"
            )
        ).flatten(),
        illegal = "</",
        contains = listOf(
            hljs.C_LINE_COMMENT_MODE,
            hljs.C_BLOCK_COMMENT_MODE,
            hljs.QUOTE_STRING_MODE,
            hljs.APOS_STRING_MODE,
            hljs.C_NUMBER_MODE,
            Mode(
                className = "meta",
                begin = "#",
                end = "$"
            ),
            Mode(
                className = "class",
                beginKeywords = keywords("surface displacement light volume imager"),
                end = "\\("
            ),
            Mode(
                beginKeywords = keywords("illuminate illuminance gather"),
                end = "\\("
            )
        )
    )
}
