package com.codewaves.codehighlight.languages
import com.codewaves.codehighlight.core.*
/*
Language = LLVM IR
Author = Michael Rodler <contact@f0rki.at>
Description = language used as intermediate representation in the LLVM compiler framework
Category = assembler
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/llvm.js MD5 <3ceaf1220a58647d6f8aaa30cc817671>
 */
internal fun llvm(): Mode {
    var identifier = "([-a-zA-Z\$._][\\w\\-\$.]*)"
    return Mode(
        // lexemes: "[.%]?" + hljs.IDENT_RE,
        keywords =
            keywords("begin end true false declare define global constant private linker_private internal available_externally linkonce linkonce_odr weak weak_odr appending dllimport dllexport common default hidden protected extern_weak external thread_local zeroinitializer undef null to tail target triple datalayout volatile nuw nsw nnan ninf nsz arcp fast exact inbounds align addrspace section alias module asm sideeffect gc dbg linker_private_weak attributes blockaddress initialexec localdynamic localexec prefix unnamed_addr ccc fastcc coldcc x86_stdcallcc x86_fastcallcc arm_apcscc arm_aapcscc arm_aapcs_vfpcc ptx_device ptx_kernel intel_ocl_bicc msp430_intrcc spir_func spir_kernel x86_64_sysvcc x86_64_win64cc x86_thiscallcc cc c signext zeroext inreg sret nounwind noreturn noalias nocapture byval nest readnone readonly inlinehint noinline alwaysinline optsize ssp sspreq noredzone noimplicitfloat naked builtin cold nobuiltin noduplicate nonlazybind optnone returns_twice sanitize_address sanitize_memory sanitize_thread sspstrong uwtable returned type opaque eq ne slt sgt sle sge ult ugt ule uge oeq one olt ogt ole oge ord uno ueq une x acq_rel acquire alignstack atomic catch cleanup filter inteldialect max min monotonic nand personality release seq_cst singlethread umax umin unordered xchg add fadd sub fsub mul fmul udiv sdiv fdiv urem srem frem shl lshr ashr and or xor icmp fcmp phi call trunc zext sext fptrunc fpext uitofp sitofp fptoui fptosi inttoptr ptrtoint bitcast addrspacecast select va_arg ret br switch invoke unwind unreachable indirectbr landingpad resume malloc alloca free load store getelementptr extractelement insertelement shufflevector getresult extractvalue insertvalue atomicrmw cmpxchg fence argmemonly double"),
        contains = listOf(
            Mode(
                className = "keyword",
                begin = "i\\d+"
            ),
            hljs.COMMENT(
                ";",
                "\\n",
                Mode(relevance = 0)
            ),
            // Double quote string
            hljs.QUOTE_STRING_MODE,
            Mode(
                className = "string",
                variants = listOf(
                    // Double-quoted string
                    Mode(
                        begin = "\"",
                        end = "[^\\\\]\""
                    )
                ),
                relevance = 0
            ),
            Mode(
                className = "title",
                variants = listOf(
                    Mode(
                        begin = "@" +
                            identifier
                    ),
                    Mode(begin = "@\\d+"),
                    Mode(
                        begin = "!" +
                            identifier
                    ),
                    Mode(
                        begin = "!\\d+" +
                            identifier
                    )
                )
            ),
            Mode(
                className = "symbol",
                variants = listOf(
                    Mode(
                        begin = "%" +
                            identifier
                    ),
                    Mode(begin = "%\\d+"),
                    Mode(begin = "#\\d+")
                )
            ),
            Mode(
                className = "number",
                variants = listOf(
                    Mode(begin = "0[xX][a-fA-F0-9]+"),
                    Mode(begin = "-?\\d+(?:[.]\\d+)?(?:[eE][-+]?\\d+(?:[.]\\d+)?)?")
                ),
                relevance = 0
            )
        )
    )
}
