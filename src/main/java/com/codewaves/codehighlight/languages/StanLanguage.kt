package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Stan
Author = Brendan Rocks <rocks.brendan@gmail.com>
 Category = scientific
Description = The Stan probabilistic programming language (http://mc-stan.org/).
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class StanLanguage : LanguageBuilder  {
  override fun build() = Mode(
    contains = listOf(
      hljs.HASH_COMMENT_MODE,
      hljs.C_LINE_COMMENT_MODE,
      hljs.C_BLOCK_COMMENT_MODE,
      Mode(
        begin = hljs.UNDERSCORE_IDENT_RE,
        lexemes = hljs.UNDERSCORE_IDENT_RE,
        keywords = listOf(
          // Stan's keywords
          Keyword(className = "name",

 value = "for in while repeat until if then else")
          // Stan's probablity distributions (less beta and gamma, as commonly
          // used for parameter names). So far, _log and _rng variants are not
          // included
          Keyword(className = "symbol",

 value = "bernoulli bernoulli_logit binomial binomial_logit beta_binomial hypergeometric categorical categorical_logit ordered_logistic neg_binomial neg_binomial_2 neg_binomial_2_log poisson poisson_log multinomial normal exp_mod_normal skew_normal student_t cauchy double_exponential logistic gumbel lognormal chi_square inv_chi_square scaled_inv_chi_square exponential inv_gamma weibull frechet rayleigh wiener pareto pareto_type_2 von_mises uniform multi_normal multi_normal_prec multi_normal_cholesky multi_gp multi_gp_cholesky multi_student_t gaussian_dlm_obs dirichlet lkj_corr lkj_corr_cholesky wishart inv_wishart"),
          // Stan's data types
          Keyword(className = "selector-tag",

 value = "int real vector simplex unit_vector ordered positive_ordered row_vector matrix cholesky_factor_corr cholesky_factor_cov corr_matrix cov_matrix"),
          // Stan's model blocks
          Keyword(className = "title",

 value = "functions model data parameters quantities transformed generated"),
          Keyword(className = "literal",

 value = "true false"
        )),
        relevance = 0
      ),
      // The below is all taken from the R language definition
      Mode(
        // hex value
        className = "number",

        begin = "0[xX][0-9a-fA-F]+[Li]?\\b",

        relevance = 0
      ),
      Mode(
        // hex value
        className = "number",

        begin = "0[xX][0-9a-fA-F]+[Li]?\\b",

        relevance = 0
      ),
      Mode(
        // explicit integer
        className = "number",

        begin = "\\d+(?:[eE][+\\-]?\\d*)?L\\b",

        relevance = 0
      ),
      Mode(
        // number with trailing decimal
        className = "number",

        begin = "\\d+\\.(?!\\d)(?:i\\b)?",

        relevance = 0
      ),
      Mode(
        // number
        className = "number",

        begin = "\\d+(?:\\.\\d*)?(?:[eE][+\\-]?\\d*)?i?\\b",

        relevance = 0
      ),
      Mode(
        // number with leading decimal
        className = "number",

        begin = "\\.\\d+(?:[eE][+\\-]?\\d*)?i?\\b",

        relevance = 0
      )
    )
  );
}
